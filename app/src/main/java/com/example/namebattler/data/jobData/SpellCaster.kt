package com.example.namebattler.data.jobData

import com.example.namebattler.data.battleData.ActionResultHolder
import com.example.namebattler.data.battleData.CharacterInformationHolder
import com.example.namebattler.data.skillAndMagic.SkillData
import com.example.namebattler.data.skillAndMagic.Skills
import com.example.namebattler.util.Belong
import com.example.namebattler.util.OperationIdEnum
import com.example.namebattler.util.SKillEnum
import com.example.namebattler.util.TargetIdEnum

private const val SUPPLY_HP = 100
private const val SUPPLY_MP = 25
private const val SUPPLY_STR = 0
private const val SUPPLY_DEF = 15
private const val SUPPLY_AGI = 5
private const val SUPPLY_LUCK = 20

class SpellCaster : JobManager.JobAbstract() {

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
        val skillData: SkillData
        val targetId: Int
        val isMagicDamage: Boolean


        //ログテキスト
        var setText = mutableListOf<String>()
        //setText.add(character.name + "は")

        when (skillName) {
            SKillEnum.THUNDERBOLT.name -> {
                skillData = Skills().thunderbolt(character)
                targetId = TargetIdEnum.ONE_ATTACK.id
                setText.add(character.name + "は" + skillData.flavorText)
                isMagicDamage = true
            }
            SKillEnum.FIREBALL.name -> {
                skillData = Skills().fireBall(character)
                targetId = TargetIdEnum.ALL_ATTACK.id
                setText.add(character.name + "は" + skillData.flavorText)
                isMagicDamage = true
            }
            else -> {
                skillData = Skills().rodAttack(character)
                targetId = TargetIdEnum.ONE_ATTACK.id
                setText.add(character.name + "は" + skillData.flavorText)
                isMagicDamage = false
            }
        }

        val result = emptyResult()
        result.flavorText = setText
        result.damageToHp = skillData.resultPoint
        result.costToMp = skillData.costMp
        result.changingCond = skillData.giveCond
        result.targetId = targetId
        result.isMagicDamage = isMagicDamage

        return result
    }

    override fun defensiveAction(
        character: CharacterInformationHolder,
        skillName: String
    ): ActionResultHolder {
        val skillData: SkillData
        val targetId: Int

        //ログテキスト
        val setText = mutableListOf<String>()

        if (skillName == SKillEnum.REFRESH.name) {
            skillData = Skills().refresh(character)
            targetId = TargetIdEnum.ONE_SUPPORT.id
            setText.add(character.name + "は" + skillData.flavorText)
        } else {
            skillData = Skills().sleepCloud(character)
            targetId = TargetIdEnum.ALL_ATTACK.id
            setText.add(character.name + "は" + skillData.flavorText)
        }

        val result = emptyResult()
        result.flavorText = setText
        result.damageToHp = skillData.resultPoint
        result.costToMp = skillData.costMp
        result.changingCond = skillData.giveCond
        result.targetId = targetId

        return result
    }

    override fun flexibleAction(
        character: CharacterInformationHolder,
        skillName: String
    ): ActionResultHolder {
        val skillData: SkillData
        val targetId: Int
        val isMagicDamage: Boolean

        //ログテキスト
        var setText = mutableListOf<String>()

        val selectSpell = (0..2).random()
        if (selectSpell == 0) {
            skillData = Skills().thunderbolt(character)
            targetId = TargetIdEnum.ONE_ATTACK.id
            setText.add(character.name + "は" + skillData.flavorText)
            isMagicDamage = true
        } else if (selectSpell == 1) {
            skillData = Skills().fireBall(character)
            targetId = TargetIdEnum.ALL_ATTACK.id
            setText.add(character.name + "は" + skillData.flavorText)
            isMagicDamage = true
        } else {
            skillData = Skills().sleepCloud(character)
            targetId = TargetIdEnum.ALL_ATTACK.id
            setText.add(character.name + "は" + skillData.flavorText)
            isMagicDamage = false
        }

        val result = emptyResult()
        result.flavorText = setText
        result.damageToHp = skillData.resultPoint
        result.costToMp = skillData.costMp
        result.changingCond = skillData.giveCond
        result.targetId = targetId
        result.isMagicDamage = isMagicDamage

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

        when (operationName) {
            OperationIdEnum.OFFENSIVE.text -> {
                val selectSpell = (0..1).random()
                //mp残量チェック
                if (selectSpell == 0 && characterHolder.currentMp >= 30) {
                    targetCharacter = enemyList
                    actionName = SKillEnum.FIREBALL.name

                } else if (selectSpell == 1 || characterHolder.currentMp >= 20) {
                    targetCharacter!!.add(enemyList.random())
                    actionName = SKillEnum.THUNDERBOLT.name

                } else {
                    targetCharacter!!.add(enemyList.random())
                    actionName = SKillEnum.ONE_MELEE_ATTACK.name
                }
            }
            OperationIdEnum.DEFENSIVE.text -> {
                val needOfRescueList = buddyDiagnosis(operationName,characterHolder.currentMp , buddyList)
                actionName = needOfRescueList.first
                        when (actionName) {
                            SKillEnum.REFRESH.name ->
                                targetCharacter.add(needOfRescueList.second.random())
                            SKillEnum.SLEEP_CLOUD.name, SKillEnum.ONE_MELEE_ATTACK.name ->
                                targetCharacter = enemyList

                        }
            }
            OperationIdEnum.FLEXIBLE.text ->{
                val selectSpell = (0..2).random()
                //mp残量チェック
                if (selectSpell == 0 && characterHolder.currentMp >= 30) {
                    targetCharacter = enemyList
                    actionName = SKillEnum.FIREBALL.name

                } else if (selectSpell == 1 || characterHolder.currentMp >= 20) {
                    targetCharacter.add(enemyList.random())
                    actionName = SKillEnum.THUNDERBOLT.name

                } else if(selectSpell == 2 || characterHolder.currentMp >= 10){
                    targetCharacter = enemyList
                    actionName = SKillEnum.SLEEP_CLOUD.name


                } else {
                    targetCharacter!!.add(enemyList.random())
                    actionName = SKillEnum.ONE_MELEE_ATTACK.name
                }
            }
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

        currentBuddyList.forEach {
            when (operationName) {
                OperationIdEnum.OFFENSIVE.text, OperationIdEnum.FLEXIBLE.text -> {
                    listOfWounded = mutableListOf()
                }
                OperationIdEnum.DEFENSIVE.text -> {
                    if (it.cond.isNotEmpty() && currentMp >= 10) {
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
                if(currentMp >= 10){
                    actionName = SKillEnum.SLEEP_CLOUD.name
                }else{
                    actionName = SKillEnum.ONE_MELEE_ATTACK.name
                }

            }
        }
        //Log.d("RESCUE", actionName)
        return Pair(actionName, resultList)
    }

    private fun emptyResult(): ActionResultHolder {
        return ActionResultHolder(mutableListOf(), 0, 0, 0, 0, Pair("", 0), 0, false)
    }

    override fun getPercent(num: Int?, standardValue: Int): Int {
        return num!! * 100 / standardValue
    }
}