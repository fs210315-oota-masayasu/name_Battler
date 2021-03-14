package com.example.namebattler.function.job

import com.example.namebattler.function.action.ActionHolder
import com.example.namebattler.function.action.meleeAttack.MaceAttack
import com.example.namebattler.function.action.supportAction.CureWounds
import com.example.namebattler.function.action.supportAction.Refresh
import com.example.namebattler.function.battle.ActionResultHolder
import com.example.namebattler.function.battle.CharacterInformationHolder
import com.example.namebattler.function.Belong
import com.example.namebattler.function.OperationIdEnum
import com.example.namebattler.function.SKillEnum
import com.example.namebattler.function.TargetIdEnum

private const val SUPPLY_HP = 150
private const val SUPPLY_MP = 25
private const val SUPPLY_STR = 10
private const val SUPPLY_DEF = 5
private const val SUPPLY_AGI = 0
private const val SUPPLY_LUCK = 20

class Priest : JobParameterProduction.JobAbstract() {
    override var hp: Int = 0
    override var mp: Int = 0
    override var str: Int = 0
    override var def: Int = 0
    override var agi: Int = 0
    override var luck: Int = 0

    override var characteristicJobParameter: List<Int> =
        listOf(SUPPLY_HP, SUPPLY_MP, SUPPLY_STR, SUPPLY_DEF, SUPPLY_AGI, SUPPLY_LUCK)

    override fun addParam() {
        this.uniqueVale()
    }

    override fun uniqueVale() {
        this.hp = characteristicJobParameter[0]
        this.mp = characteristicJobParameter[1]
        this.str = characteristicJobParameter[2]
        this.def = characteristicJobParameter[3]
        this.agi = characteristicJobParameter[4]
        this.luck = characteristicJobParameter[5]
    }

    /** PARAM: ActionResultHolder
     * val flavorText: MutableList<String>,
     * val damageToHp: Int,
     * val cureToHp : Int,
     * val costToMp: Int,
     * val buffToDef: Int,
     * val changingCond:String,
     * val targetId: Int
     */

    override fun offensiveAction(
        character: CharacterInformationHolder,
        skillName: String
    ): ActionResultHolder {
        //val skillData = Skills().maceAttack(character)
        val skillData = MaceAttack().activeAction(character)

        //ログテキスト
        var setText = mutableListOf<String>()
        setText.add(character.name + "は" + skillData.flavorText)
        if (skillData.isCritical) {
            setText.add("会心の一撃！")
        }

        val result = ActionResultHolder()
        result.flavorText = setText
        result.damageToHp = skillData.resultPoint
        result.costToMp = skillData.costMp
        result.changingCond = skillData.giveCond
        result.targetId = TargetIdEnum.ONE_ATTACK.id

        return result
    }

    override fun defensiveAction(
        character: CharacterInformationHolder,
        skillName: String
    ): ActionResultHolder {
        var actionHolder: ActionHolder
        val result = ActionResultHolder()
        //ログテキスト
        var setText = mutableListOf<String>()
        //setText.add(character.name + "は")

        if (skillName == SKillEnum.CURE_WOUNDS.name) {
            actionHolder = CureWounds().activeAction(character)
            setText.add(character.name + "は" + actionHolder.flavorText)

            result.flavorText = setText
            result.damageToHp = 0
            result.cureToHp = actionHolder.resultPoint
            result.costToMp = actionHolder.costMp
            result.buffToDef = 0
            result.changingCond = actionHolder.giveCond
            result.targetId = TargetIdEnum.ONE_SUPPORT.id

        } else if (skillName == SKillEnum.REFRESH.name) {
            actionHolder = Refresh().getSkillData(character)
            setText.add(character.name + "は" + actionHolder.flavorText)

            result.flavorText = setText
            result.damageToHp = 0
            result.cureToHp = actionHolder.resultPoint
            result.costToMp = actionHolder.costMp
            result.buffToDef = 0
            result.changingCond = actionHolder.giveCond
            result.targetId = TargetIdEnum.ONE_SUPPORT.id

        } else {
            actionHolder = MaceAttack().getSkillData(character)
            setText.add(character.name + "は" + actionHolder.flavorText)
            if (actionHolder.isCritical) {
                setText.add("会心の一撃！")
            }
            result.flavorText = setText
            result.damageToHp = actionHolder.resultPoint
            result.costToMp = actionHolder.costMp
            result.changingCond = actionHolder.giveCond
            result.targetId = TargetIdEnum.ONE_ATTACK.id
        }
        return result
    }
    /** PARAM: ActionResultHolder
     * val flavorText: MutableList<String>,
     * val damageToHp: Int,
     * val cureToHp : Int,
     * val costToMp: Int,
     * val buffToDef: Int,
     * val changingCond:String,
     * val targetId: Int
     */
    override fun flexibleAction(
        character: CharacterInformationHolder,
        skillName: String
    ): ActionResultHolder {
        var actionHolder: ActionHolder
        val result = ActionResultHolder()
        //ログテキスト
        var setText = mutableListOf<String>()
        //setText.add(character.name + "は")

        if (skillName == SKillEnum.CURE_WOUNDS.name) {
            actionHolder = CureWounds().getSkillData(character)
            setText.add(character.name + "は" + actionHolder.flavorText)

            result.flavorText = setText
            result.damageToHp = 0
            result.cureToHp = actionHolder.resultPoint
            result.costToMp = actionHolder.costMp
            result.buffToDef = 0
            result.changingCond = actionHolder.giveCond
            result.targetId = TargetIdEnum.ONE_SUPPORT.id

        } else if (skillName == SKillEnum.REFRESH.name) {
            actionHolder = Refresh().getSkillData(character)
            setText.add(character.name + "は" + actionHolder.flavorText)

            result.flavorText = setText
            result.damageToHp = 0
            result.cureToHp = actionHolder.resultPoint
            result.costToMp = actionHolder.costMp
            result.buffToDef = 0
            result.changingCond = actionHolder.giveCond
            result.targetId = TargetIdEnum.ONE_SUPPORT.id
        } else {
            actionHolder = MaceAttack().getSkillData(character)
            setText.add(character.name + "は" + actionHolder.flavorText)
            if (actionHolder.isCritical) {
                setText.add("会心の一撃！")
            }
            result.flavorText = setText
            result.damageToHp = actionHolder.resultPoint
            result.costToMp = actionHolder.costMp
            result.changingCond = actionHolder.giveCond
            result.targetId = TargetIdEnum.ONE_ATTACK.id
        }
        return result
    }

    override fun selectSkill(
        operationName: String,
        characterHolder: CharacterInformationHolder,
        currentInformation: MutableList<CharacterInformationHolder>
    ): Pair<String, MutableList<CharacterInformationHolder>> {

        val belong = characterHolder.belong

        //ステータス情報を敵味方ごとに分割
        val enemyList: MutableList<CharacterInformationHolder>
        val buddyList: MutableList<CharacterInformationHolder>

        when (belong) {
            Belong.ENEMY.name -> {
                enemyList =
                    currentInformation.filter { it.belong == Belong.PLAYER.name }.toMutableList()
                buddyList =
                    currentInformation.filter { it.belong == Belong.ENEMY.name }.toMutableList()
            }
            Belong.PLAYER.name -> {
                enemyList =
                    currentInformation.filter { it.belong == Belong.ENEMY.name }.toMutableList()
                buddyList =
                    currentInformation.filter { it.belong == Belong.PLAYER.name }.toMutableList()
            }
            else -> {
                enemyList = mutableListOf()
                buddyList = mutableListOf()
            }
        }

        var targetCharacter = mutableListOf<CharacterInformationHolder>()
        var actionName: String? = null

        if(enemyList.isNotEmpty()){
            //僧侶クラスには複数目標のスキルはなし
            when (operationName) {
                OperationIdEnum.OFFENSIVE.text -> {
                    targetCharacter!!.add(enemyList.random())
                    actionName = SKillEnum.ONE_MELEE_ATTACK.name
                }
                OperationIdEnum.DEFENSIVE.text, OperationIdEnum.FLEXIBLE.text -> {
                    val needOfRescueList =
                        buddyDiagnosis(operationName, characterHolder.currentMp, buddyList)
                    actionName = needOfRescueList.first
                    when (actionName) {
                        SKillEnum.CURE_WOUNDS.name, SKillEnum.REFRESH.name ->
                            targetCharacter!!.add(needOfRescueList.second.random())

                        SKillEnum.ONE_MELEE_ATTACK.name ->
                            targetCharacter!!.add(enemyList.random())
                    }
                }
            }
        }else{
            actionName = "NONE"
            targetCharacter.add(CharacterInformationHolder())
        }



        return Pair(actionName ?: "", targetCharacter!!)
    }

    private fun buddyDiagnosis(
        operationName: String,
        currentMp: Int,
        currentBuddyList: MutableList<CharacterInformationHolder>
    ):
            Pair<String, MutableList<CharacterInformationHolder>> {
        var resultList = mutableListOf<CharacterInformationHolder>()
        var actionName: String
        var listOfWounded = mutableListOf<CharacterInformationHolder>()
        var listOfBadCond = mutableListOf<CharacterInformationHolder>()

        //味方の情報を順に参照
        currentBuddyList.forEach {
            //HPの残り％を算出
            val stateOfHp = getPercent(it.currentHp, it.maxHp)
            //作戦ごとに処理を分ける
            when (operationName) {
                OperationIdEnum.OFFENSIVE.text -> {
                    listOfWounded = mutableListOf()
                }
                //負傷or状態異常かつ、僧侶のMPが足りている場合にリストアップする
                OperationIdEnum.DEFENSIVE.text -> {
                    if (stateOfHp < 100 && currentMp >= 20) {
                        listOfWounded.add(it)
                    } else if (it.cond.isNotEmpty() && currentMp >= 10) {
                        listOfBadCond.add(it)
                    }
                }
                OperationIdEnum.FLEXIBLE.text -> {
                    if (stateOfHp < 50 && currentMp >= 20) {
                        listOfWounded.add(it)
                    } else if (it.cond.isNotEmpty() && currentMp >= 10) {
                        listOfBadCond.add(it)
                    }
                }
            }
        }
        //returnするリストを決定（listOfRescueが空であればlistOfBadCondをreturn）
        //どちらも空なら空データをreturnする
        when {
            listOfWounded.isNotEmpty() -> {
                actionName = SKillEnum.CURE_WOUNDS.name
                resultList = listOfWounded
            }
            listOfBadCond.isNotEmpty() -> {
                actionName = SKillEnum.REFRESH.name
                resultList = listOfBadCond
            }
            else -> {
                actionName = SKillEnum.ONE_MELEE_ATTACK.name
            }
        }
        return Pair(actionName, resultList)
    }

    override fun getPercent(num: Int?, standardValue: Int): Int {
        return num!! * 100 / standardValue
    }
}