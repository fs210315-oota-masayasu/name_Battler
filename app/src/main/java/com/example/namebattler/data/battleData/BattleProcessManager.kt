package com.example.namebattler.data.battleData

import com.example.namebattler.data.actionData.SkillData
import com.example.namebattler.data.actionData.Skills
import com.example.namebattler.util.ConditionEnum
import com.example.namebattler.util.DamageTypeEnum

class BattleProcessManager {

    private var characterInformation = arrayListOf<CharacterInformationHolder>()
    private var deadList = arrayListOf<CharacterInformationHolder>()

    fun setBattleProcessInformation(
        characterInformation: ArrayList<CharacterInformationHolder>,
        deadList: ArrayList<CharacterInformationHolder>
    ) {
        this.characterInformation = characterInformation
        this.deadList = deadList
    }

    fun attackProcess(
        targetList: MutableList<CharacterInformationHolder>,
        attackResult: ActionResultHolder
    ): MutableList<String> {
        val resultLog = mutableListOf<String>()

        targetList.forEach { targetInformation ->
            //攻撃結果反映
            //防御判定
            val defPoint: SkillData = if (attackResult.isMagicDamage) {
                Skills().magicDefense(targetInformation)
            } else {
                Skills().meleeDefense(targetInformation)
            }
            var damage = 0
            var whoIs = targetInformation.name + "は"

            val isDamage = checkDamageType(attackResult, defPoint)
            if (isDamage == DamageTypeEnum.NORMAL_DAMAGE.name) {
                damage = attackResult.damageToHp + (attackResult.damageToHp / 10 * attackResult.turnCorrection)

                //ログ設定
                val whatDamage = damage.toString() + "のダメージをおった"
                resultLog.add(whoIs + whatDamage)

            } else if(isDamage == DamageTypeEnum.NO_DAMAGE.name) {    //非殺傷（眠り）の攻撃を除く
                var whatDo = ""
                whatDo = if (targetInformation.cond.containsKey(ConditionEnum.SLEEP.text)){
                    "ダメージを受けなかった"
                }else{
                    defPoint.flavorText
                }
                //ログ設定
                resultLog.add(whoIs + whatDo)
            }
            if (damage > 0) {
                if (targetInformation.currentHp > damage) {
                    targetInformation.currentHp = targetInformation.currentHp - damage
                } else {
                    targetInformation.currentHp = 0
                    deadList.add(targetInformation)

                    //状態異常を「死亡」のみにする
                    targetInformation.cond.clear()
                    targetInformation.cond[ConditionEnum.DEAD.text] = 0

                    val whatHappened = "倒れた"
                    resultLog.add(whoIs + whatHappened)
                }
            }
            //状態異常の付与判定（戦闘不能にした場合はスキップする）
            if (targetInformation.currentHp > 0) {

                if (attackResult.changingCond.first != ""){

                    //状態異常の抵抗判定
                    val whoIs = targetInformation.name + "は"
                    val whatCond: String

                    val isResist: Boolean = if (damage > 0 || attackResult.changingCond.first == ConditionEnum.SLEEP.text){
                        resistBadStatus(targetInformation)
                    }else{
                        true
                    }
                    if (!isResist) {
                        whatCond =
                            textOfBadStatus(attackResult.changingCond.first)

                        //重複する場合は上書き
                        if (attackResult.changingCond.first != ConditionEnum.FRESH.text) {
                            targetInformation.cond.plusAssign(attackResult.changingCond.first to attackResult.changingCond.second)
                        }
                    }else{
                        whatCond = "抵抗した"
                    }
                    //ログ設定
                    resultLog.add(whoIs + whatCond)
                }
            }
            //標的ごとに情報を更新
            val targetId = targetInformation.id
            characterInformation[targetId] = targetInformation
        }
        resultLog.add("")
        return resultLog
    }

    fun myselfSupportProcess(doerId: Int, attackResult: ActionResultHolder): MutableList<String> {
        val resultLog = mutableListOf<String>()
        characterInformation[doerId].effectTimeOfDef = attackResult.buffToDef
        resultLog.add("${characterInformation[doerId].name}の防御力がアップした")

        return resultLog
    }


    fun supportProcess(
        targetList: MutableList<CharacterInformationHolder>,
        attackResult: ActionResultHolder
    ): MutableList<String> {
        val resultLog = mutableListOf<String>()
        val targetInformation = targetList.firstOrNull()
        if (targetInformation != null) {
            if (attackResult.changingCond.first == ConditionEnum.FRESH.text) {
                targetInformation.cond = mutableMapOf()
                resultLog.add("${targetInformation.name}は元気になった")
            } else {
                val afterCureHp =
                    targetInformation.currentHp + attackResult.cureToHp
                if (targetInformation.maxHp < afterCureHp) {
                    targetInformation.currentHp = targetInformation.maxHp
                } else {
                    targetInformation.currentHp = afterCureHp
                }
                resultLog.add("${targetInformation.name}のHPが回復した")
            }
            //標的（支援相手）の情報を更新
            var targetId = targetInformation.id
            characterInformation[targetId] = targetInformation
        }
        return resultLog
    }

    fun conditionProcess(
        doerId: Int,
        doerCharacter: CharacterInformationHolder
    ): Pair<Boolean, MutableList<String>> {
        val resultLog = mutableListOf<String>()

        //コンディション情報取得
        var badStatusList = mutableMapOf<String, Int>()
        if (characterInformation[doerId].cond.isNotEmpty()) {
            badStatusList = characterInformation[doerId].cond
        }

        //状態異常の処理を適用
        //ダメージ処理を先に済ませてから行動不能処理を適用
        //【炎上】
        val isScald = badStatusList.containsKey(ConditionEnum.SCALD.text)
        //ログ設定
        val whoIs = doerCharacter.name + "は"
        var whatLog: String
        if (isScald) {
            val scaldTurn = badStatusList[ConditionEnum.SCALD.text] ?: 0
            //「scald」に値が格納されている場合、ダメージ処理を実行

            if (scaldTurn > 0) {
                val damage = doerCharacter.currentHp / 10
                characterInformation[doerId].currentHp = doerCharacter.currentHp - damage

                whatLog = damage.toString() + "の炎上ダメージをうけた"
            }else{                //valueが0の場合はmapから削除する
                characterInformation[doerId].cond.remove(ConditionEnum.SCALD.text)
                whatLog = "炎上状態から回復した"

            }
            resultLog.add(whoIs + whatLog)
        }
        //【麻痺】
        val isParalysis = badStatusList.containsKey(ConditionEnum.PARALYSIS.text)
        if (isParalysis) {
            val paralysisTurn = badStatusList[ConditionEnum.PARALYSIS.text] ?: 0
            //「paralysis」に値が格納されている場合、デバフ処理を実行
            //ログ設定
            if (paralysisTurn > 0) {
                characterInformation[doerId].effectTimeOfStr = doerCharacter.effectTimeOfStr - 1
                characterInformation[doerId].effectTimeOfDef = doerCharacter.effectTimeOfDef - 1
                characterInformation[doerId].effectTimeOfAgi = doerCharacter.effectTimeOfAgi - 1
                characterInformation[doerId].effectTimeOfLuck = doerCharacter.effectTimeOfLuck - 1

                whatLog = "体が痺れている！"

            }else{                //valueが0の場合はmapから削除する
                characterInformation[doerId].cond.remove(ConditionEnum.PARALYSIS.text)
                whatLog = "麻痺状態から回復した"
            }
            resultLog.add(whoIs + whatLog)
        }
        //行動処理可否
        var isActive = true
        //【眠り】
        val isSleep = badStatusList.containsKey(ConditionEnum.SLEEP.text)
        if (isSleep) {
            val sleepTurn = badStatusList[ConditionEnum.SLEEP.text] ?: 0

            if (sleepTurn > 0) {
                isActive = false
                //ログ
                whatLog = "眠っている"
                resultLog.add(whoIs + whatLog)
                resultLog.add("")
             }else {                //valueが0の場合はmapから削除する
                characterInformation[doerId].cond.remove(ConditionEnum.SLEEP.text)
                whatLog = "目が覚めた"
                resultLog.add(whoIs + whatLog)
            }
        }
        //mapがnullになった場合に空データを格納する
        if (characterInformation[doerId].cond.isNullOrEmpty()){
            characterInformation[doerId].cond = mutableMapOf()
        }

        return Pair(isActive, resultLog)
    }

    //MPコスト支払い(マイナスになる場合は0を格納)
    fun mpPayment(doerId: Int, attackResult: ActionResultHolder) {
        if (characterInformation[doerId].currentMp > attackResult.costToMp) {
            characterInformation[doerId].currentMp =
                characterInformation[doerId].currentMp - attackResult.costToMp
        } else {
            characterInformation[doerId].currentMp = 0
        }
    }

    //各Buffのターン数更新
    fun updateCurrentInformation(doerId: Int) {
        characterInformation[doerId].effectTimeOfStr =
            checkEffectTime(characterInformation[doerId].effectTimeOfStr)
        characterInformation[doerId].effectTimeOfDef =
            checkEffectTime(characterInformation[doerId].effectTimeOfDef)
        characterInformation[doerId].effectTimeOfAgi =
            checkEffectTime(characterInformation[doerId].effectTimeOfAgi)
        characterInformation[doerId].effectTimeOfLuck =
            checkEffectTime(characterInformation[doerId].effectTimeOfLuck)
        // 状態異常の更新

        characterInformation[doerId].cond = characterInformation[doerId].cond.mapValues {
            it.value - 1
        }.toMutableMap()


    }

    //ダメージタイプの確認
    private fun checkDamageType(attackResult: ActionResultHolder, defPoint: SkillData): String{
        return when {
            attackResult.damageToHp > defPoint.resultPoint -> {
                DamageTypeEnum.NORMAL_DAMAGE.name
            }
            attackResult.changingCond.first == ConditionEnum.SLEEP.text -> {
                DamageTypeEnum.NON_LETHAL_DAMAGE.name
            }
            else -> {
                DamageTypeEnum.NO_DAMAGE.name
            }
        }
    }

    private fun checkEffectTime(effectTimeVale: Int): Int {

        var resultValue = 0

        if (effectTimeVale < 0) {
            resultValue = effectTimeVale + 1
        } else if (effectTimeVale > 0) {
            resultValue = effectTimeVale - 1
        }

        return resultValue

    }

    private fun resistBadStatus(character: CharacterInformationHolder): Boolean {

        var isResist = false
        val standardValue = getPercent(character.luck)
        val resistRate = (0..100).random()

        if (standardValue >= resistRate) {
            isResist = true
        }
        return isResist
    }

    private fun textOfBadStatus(cond: String): String {

        var log = ""

        when (cond) {
            ConditionEnum.SLEEP.text -> log = "眠ってしまった"
            ConditionEnum.PARALYSIS.text -> log = "麻痺してしまった"
            ConditionEnum.SCALD.text -> log = "体が燃えている"
        }
        return log
    }

    private fun getPercent(num: Int?): Int {
        return num!! * 100 / 255
    }

}