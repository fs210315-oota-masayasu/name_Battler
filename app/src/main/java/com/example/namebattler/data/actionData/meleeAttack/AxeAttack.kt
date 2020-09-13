package com.example.namebattler.data.actionData.meleeAttack

import com.example.namebattler.data.actionData.ActionInterface
import com.example.namebattler.data.actionData.SkillData
import com.example.namebattler.data.battleData.CharacterInformationHolder

class AxeAttack : ActionInterface {
    override fun getSkillData(character: CharacterInformationHolder): SkillData {
        return activeAction(character)
    }

    override fun activeAction(character: CharacterInformationHolder): SkillData {
        //攻撃基準値
        //buff,deBuffの適用
        val correctionStrValue = character.effectTimeOfStr
        var str = character.str

        if (correctionStrValue > 0) {
            str = str * 3 / 2
        } else if (correctionStrValue < 0) {
            str = str * 2 / 3
        }

        //命中基準値
        val correctionLuckVale = character.effectTimeOfLuck
        var luck = character.luck
        if (correctionLuckVale > 0) {
            luck = luck * 3 / 2
        } else if (correctionLuckVale < 0) {
            luck = luck * 2 / 3
        }
        //命中補正値
        var hitRateCorrection = 100 - getPercent(character.currentHp, character.maxHp)

        var hitRateStandard = 0
        if (getPercent(luck) - 30 + hitRateCorrection > 0) {
            hitRateStandard = getPercent(luck) - 20 + hitRateCorrection
        }
        //命中率（低目有利）
        val hitRate = (0..100).random()
        //命中値（攻撃補正値）
        val hittingPoint: Int

        //クリティカル成否
        var isCritical = false

        //クリティカルヒット
        if (hitRate <= hitRateStandard / 10) {
            isCritical = true
            hittingPoint = str / 2 + hitRateStandard
        } else {
            hittingPoint = (100 - hitRate) / 100 * hitRateStandard
        }

        val attackPoint = str + hittingPoint


        return SkillData(
            "斧で攻撃した",
            attackPoint,
            0,
            isCritical,
            Pair("", 0)
        )
    }

    override fun passiveDefense(character: CharacterInformationHolder): SkillData {
        return SkillData()
    }

    override fun guardAction(): SkillData {
        return SkillData()
    }


    override fun getPercent(num: Int?): Int {
        return num!! * 100 / 255
    }

    override fun getPercent(num: Int?, standardValue: Int): Int {
        return num!! * 100 / standardValue
    }
}