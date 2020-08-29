package com.example.namebattler.data.jobData

import com.example.namebattler.data.battleData.ActionResultHolder
import com.example.namebattler.data.battleData.CharacterInformationHolder
import com.example.namebattler.data.skillAndMagic.SkillData
import com.example.namebattler.data.skillAndMagic.Skills
import com.example.namebattler.util.Belong
import com.example.namebattler.util.OperationIdEnum
import com.example.namebattler.util.SKillEnum
import com.example.namebattler.util.TargetIdEnum

private const val SUPPLY_HP = 200
private const val SUPPLY_MP = 0
private const val SUPPLY_STR = 25
private const val SUPPLY_DEF = 5
private const val SUPPLY_AGI = 15
private const val SUPPLY_LUCK = 10

class Berserk : JobManager.JobAbstract() {
    override var hp: Int = 0
    override var mp: Int = 0
    override var str: Int = 0
    override var def: Int = 0
    override var agi: Int = 0
    override var luck: Int = 0

    override var supplyBox: List<Int> =
        listOf(SUPPLY_HP, SUPPLY_MP, SUPPLY_STR, SUPPLY_DEF, SUPPLY_AGI, SUPPLY_LUCK)

    override fun addParam() {
        this.uniqueVale()
    }

    override fun uniqueVale() {
        this.hp = supplyBox[0]
        this.mp = supplyBox[1]
        this.str = supplyBox[2]
        this.def = supplyBox[3]
        this.agi = supplyBox[4]
        this.luck = supplyBox[5]
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
        val skillData = Skills().axeAttack(character)

        //ログテキスト
        var setText = mutableListOf<String>()
        //setText.add(character.name + "は")
        setText.add(character.name + "は" + skillData.flavorText)
        if (skillData.isCritical) {
            setText.add("会心の一撃！")
        }

        val result = emptyResult()

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
        var skillData: SkillData
        val result = emptyResult()

        //ログテキスト
        var setText = mutableListOf<String>()

        //HP残量が最大値の80%以下の場合、50%の確率で「ぼうぎょ」
        if (skillName == SKillEnum.GUARD.name) {
            skillData = Skills().meleeGuard(character)

            setText.add(character.name + "は" + skillData.flavorText)

            result.flavorText = setText
            result.costToMp = skillData.costMp
            result.buffToDef = skillData.resultPoint
            result.changingCond = skillData.giveCond
            result.targetId = TargetIdEnum.MYSELF.id
        } else {

            skillData = Skills().axeAttack(character)

            setText.add(character.name + "は" + skillData.flavorText)
            if (skillData.isCritical) {
                setText.add("会心の一撃！")
            }
            result.flavorText = setText
            result.damageToHp = skillData.resultPoint
            result.costToMp = skillData.costMp
            result.changingCond = skillData.giveCond
            result.targetId = TargetIdEnum.ONE_ATTACK.id
        }
        return result
    }

    override fun flexibleAction(
        character: CharacterInformationHolder,
        skillName: String
    ): ActionResultHolder {
        var skillData: SkillData
        val result = emptyResult()

        //ログテキスト
        var setText = mutableListOf<String>()

        //HP残量が最大値の30%以下の場合、50%の確率で「ぼうぎょ」
        if (skillName == SKillEnum.GUARD.name) {
            skillData = Skills().meleeGuard(character)

            setText.add(character.name + "は" + skillData.flavorText)

            result.flavorText = setText
            result.costToMp = skillData.costMp
            result.buffToDef = skillData.resultPoint
            result.changingCond = skillData.giveCond
            result.targetId = TargetIdEnum.MYSELF.id
        } else {
            skillData = Skills().axeAttack(character)

            setText.add(character.name + "は")
            setText.add(skillData.flavorText)
            if (skillData.isCritical) {
                setText.add("会心の一撃！")
            }
            result.flavorText = setText
            result.damageToHp = skillData.resultPoint
            result.costToMp = skillData.costMp
            result.changingCond = skillData.giveCond
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

        when (belong) {
            Belong.ENEMY.name -> {
                enemyList =
                    currentInformation.filter { it.belong == Belong.PLAYER.name }.toMutableList()
            }
            Belong.PLAYER.name -> {
                enemyList =
                    currentInformation.filter { it.belong == Belong.ENEMY.name }.toMutableList()
            }
            else -> {
                enemyList = mutableListOf()
            }
        }

        var targetCharacter = mutableListOf<CharacterInformationHolder>()
        var actionName: String? = null

        when (operationName) {
            OperationIdEnum.OFFENSIVE.text -> {
                targetCharacter.add(enemyList.random())
                actionName = SKillEnum.ONE_MELEE_ATTACK.name
            }
            OperationIdEnum.DEFENSIVE.text -> {
                val selectActionId = (0..1).random()
                //HPの残り％を算出
                val stateOfHp = getPercent(characterHolder.currentHp, characterHolder.maxHp)
                if (stateOfHp <= 80 && selectActionId == 0) {
                    targetCharacter.add(characterHolder)
                    actionName = SKillEnum.GUARD.name
                } else {
                    targetCharacter.add(enemyList.random())
                    actionName = SKillEnum.ONE_MELEE_ATTACK.name
                }
            }
            OperationIdEnum.FLEXIBLE.text -> {
                val selectActionId = (0..1).random()
                //HPの残り％を算出
                val stateOfHp = getPercent(characterHolder.currentHp, characterHolder.maxHp)
                if (stateOfHp <= 30 && selectActionId == 0) {
                    targetCharacter!!.add(characterHolder)
                    actionName = SKillEnum.GUARD.name
                } else {
                    targetCharacter!!.add(enemyList.random())
                    actionName = SKillEnum.ONE_MELEE_ATTACK.name
                }
            }
        }
        return Pair(actionName ?: "", targetCharacter!!)
    }

    private fun emptyResult(): ActionResultHolder {
        return ActionResultHolder(mutableListOf(), 0, 0, 0, 0, Pair("", 0), 0, false)

    }

    override fun getPercent(num: Int?, standardValue: Int): Int {
        return num!! * 100 / standardValue
    }
}