package com.example.namebattler.battle

import android.util.Log
import com.example.namebattler.data.battleData.ActionResultHolder
import com.example.namebattler.data.battleData.BattleProcessManager
import com.example.namebattler.data.battleData.CharacterInformationHolder
import com.example.namebattler.data.enemyData.EnemyManager
import com.example.namebattler.data.jobData.JobManager
import com.example.namebattler.util.Belong
import com.example.namebattler.util.EndEnum
import com.example.namebattler.util.OperationIdEnum
import com.example.namebattler.util.TargetIdEnum

private const val LOG = " [ BATTLE_LOG ] "
private const val OUTPUT = " [ output_log ] "


private const val INFO = " < BattleManager >"


class BattleManager {

    var initiative: Map<Pair<String, String>, Int> = mutableMapOf()

    private var enemyList = mutableListOf<CharacterInformationHolder>()
    private var playerList = mutableListOf<CharacterInformationHolder>()

    private var currentInformation = arrayListOf<CharacterInformationHolder>()
    private var deadList = arrayListOf<CharacterInformationHolder>()



    var count = 0


    fun getInstance(): BattleManager {
        return instance
    }

    fun initCharacterList(
        enemyObj: ArrayList<CharacterInformationHolder>,
        partyObj: ArrayList<CharacterInformationHolder>
    ) {
        //初期化処理
        this.enemyList = mutableListOf()
        this.enemyList = enemyObj

        //初期化処理
        this.playerList = mutableListOf()
        this.playerList = partyObj
    }

    fun setCurrentInformation(characterInformation: ArrayList<CharacterInformationHolder>) {
        this.currentInformation = characterInformation
    }

    fun getCurrentInformation(): ArrayList<CharacterInformationHolder> {
        return currentInformation
    }

    fun initInitiative() {

        //player、enemyをまとめる
        val characterList = mutableListOf<CharacterInformationHolder?>()
        characterList.addAll(enemyList)
        characterList.addAll(playerList)

        initiative = characterList.associate { Pair(it!!.belong, it.name) to (it.agi) }.toList()
            .sortedBy { it.second }.reversed().toMap()


    }

    /** 戦闘処理 **/
    fun battleProcess(sendToOperation: String): MutableList<String> {
        val resultLog = mutableListOf<String>()
        var operation : String

        Log.d(LOG, "[ ${getCurrentInformation()} ] ")
        Log.d(LOG, "[ $count ] ターン目")
        resultLog.add("--------[$count ターン]--------")

        val instanceOfBattleProcess = BattleProcessManager()
        instanceOfBattleProcess.setBattleProcessInformation(currentInformation, deadList)
        run loop@{
            //行動順に行動する
            initiative.forEach { map ->
                //途中で勝敗が決した場合処理を抜け出す
                val isEnding = isEnding()
                if(isEnding != ""){
                    return@loop
                }
                val pair = map.key
                var doerCharacter = selectActionCharacter(pair)!!

                //敵味方で"operation"の情報が変わる
                operation = if (doerCharacter.belong == Belong.ENEMY.name){
                    EnemyManager().selectEnemyOperation()
                }else {
                    sendToOperation
                }

                Log.d(LOG, "operation is [ $operation ] ")

                //行為者のIDを取得
                val doerId = doerCharacter.id
                Log.d(LOG,"ID $doerId")

                //行為者が戦闘可能かどうか確認
                var isLiving = true
                val hp = doerCharacter.currentHp
                if (hp < 1){
                    isLiving = false
                }

                if (isLiving){
                    //resultLog.add("")
                    resultLog.add("${doerCharacter.name}の行動")

                    Log.d("CHECK_INFO", "actionChara is : $doerCharacter")

                    val resultAndLog = instanceOfBattleProcess.conditionProcess(doerId, doerCharacter)
                    resultLog.addAll(resultAndLog.second)

                    if (resultAndLog.first) {
                        val selectAttackResult = selectAttackResult(operation, doerCharacter)
                        //行動結果を取得
                        var attackResult = selectAttackResult.first
                        val selectSkill = selectAttackResult.second

                        //ログ情報設定[行動内容]
                        resultLog.addAll(attackResult.flavorText)
                        //標的情報(複数含む)
                        val targetList = selectSkill.second
                        //標的用の識別ID
                        //var targetId: Int

                        //行動結果を反映
                        when (attackResult.targetId) {
                            TargetIdEnum.ONE_ATTACK.id, TargetIdEnum.ALL_ATTACK.id -> {
                                var log = instanceOfBattleProcess.attackProcess(targetList, attackResult)
                                resultLog.addAll(log)
                            }
                            TargetIdEnum.MYSELF.id -> {
                                //防御アップのみ
                                val log = instanceOfBattleProcess.myselfSupportProcess(doerId, attackResult)
                                resultLog.addAll(log)
                            }
                            TargetIdEnum.ONE_SUPPORT.id -> {
                                val log = instanceOfBattleProcess.supportProcess(targetList, attackResult)
                                resultLog.addAll(log)
                            }
                        }
                        //MPコスト支払い(マイナスになる場合は0を格納)
                        instanceOfBattleProcess.mpPayment(doerId, attackResult)
                    }

                    //行動中のキャラの情報更新
                    instanceOfBattleProcess.updateCurrentInformation(doerId)
                    // TODO 後で消す
                    checkParamOfEffect(doerId, "更新後")
                }else{
                    //resultLog.add("${doerCharacter.name } は返事がない ただの屍のようだ")
                }
            }
        }

        Log.d(INFO, "[297]$OUTPUT is : $resultLog")
        return resultLog
    }


    private fun selectAttackResult(
        operation: String,
        actionCharacter: CharacterInformationHolder
    ): Pair<ActionResultHolder, Pair<String, MutableList<CharacterInformationHolder>>> {
        //ジョブインスタンス取得

        //selectSkillで使用スキルと対象者を選定する
        /** selectSkill param
         * ( first : skillName , second =  target) **/
        var selectSkill: Pair<String, MutableList<CharacterInformationHolder>> =
            Pair("", mutableListOf())

        //行動結果
        var attackResult = emptyResult()

        val actorJob = actionCharacter.job.let { JobManager().getJobInstance(it) }


        //このタイミングで戦闘不能者をターゲットからはじく
        val targetList = getCurrentInformation().filterNot { it.currentHp <= 0 } as ArrayList<CharacterInformationHolder>

        val dead = getCurrentInformation().filter { it.currentHp <= 0 }

        Log.d(LOG, "$INFO[421]_dead list is $dead")
        Log.d(LOG, "$INFO[423]_targetList is $targetList")

        selectSkill = actorJob!!.selectSkill(operation, actionCharacter, targetList)

        when (operation) {
            OperationIdEnum.OFFENSIVE.text -> attackResult =
                actorJob.offensiveAction(actionCharacter, selectSkill.first)
            OperationIdEnum.DEFENSIVE.text -> attackResult =
                actorJob.defensiveAction(actionCharacter, selectSkill.first)
            OperationIdEnum.FLEXIBLE.text -> attackResult =
                actorJob.flexibleAction(actionCharacter, selectSkill.first)
        }

        Log.d(LOG, "$INFO[324] AttackResult is  $attackResult")
        Log.d(LOG, "[325]selectAttackResult MP Log is ${attackResult.costToMp}")

        return Pair(attackResult, selectSkill)
    }

    fun isEnding(): String{

        var ending = ""

        var deadEnemyNum = 0
        var deadPlayerNum = 0

        deadList.forEach {
            if (it.belong == Belong.ENEMY.name){
                deadEnemyNum ++
            }else if (it.belong == Belong.PLAYER.name){
                deadPlayerNum ++
            }
        }
        if (deadEnemyNum == 3){
            ending = EndEnum.WIN.name
        }else if (deadPlayerNum == 3){
            ending = EndEnum.LOSE.name
        }
        return ending
    }


    // TODO 確認用（後で消す）
    fun checkParamOfEffect(doerId :Int, log :String){
        Log.d(LOG,"")
        Log.d(LOG,"$log[505]_effectTime[Str] (Actor):${currentInformation[doerId].name} + ${currentInformation[doerId].effectTimeOfStr}")
        Log.d(LOG,"$log[506]_effectTime[Def](Actor):${currentInformation[doerId].name} + ${currentInformation[doerId].effectTimeOfDef}")
        Log.d(LOG,"$log[507]_effectTime[Agi](Actor):${currentInformation[doerId].name} + ${currentInformation[doerId].effectTimeOfAgi}")
        Log.d(LOG,"$log[508]_effectTime[Luck](Actor):${currentInformation[doerId].name} + ${currentInformation[doerId].effectTimeOfLuck}")
        Log.d(LOG,"")
        Log.d(LOG,"$log[510]_result Cond(Actor):${currentInformation[doerId].name} + ${currentInformation[doerId].cond}")
    }


    private fun selectActionCharacter(initiativeKey: Pair<String, String>): CharacterInformationHolder? {
        var setList = mutableListOf<CharacterInformationHolder?>()

        val belong = initiativeKey.first
        val name = initiativeKey.second

        when (belong) {
            Belong.PLAYER.name -> setList = playerList.toMutableList()
            Belong.ENEMY.name -> setList = enemyList.toMutableList()
        }
        return setList.find { it!!.name == name }
    }

    private fun emptyResult(): ActionResultHolder {
        return ActionResultHolder(mutableListOf(), 0, 0, 0, 0, Pair("", 0), 0, false)
    }


    companion object {
        val instance = BattleManager()
    }

}
