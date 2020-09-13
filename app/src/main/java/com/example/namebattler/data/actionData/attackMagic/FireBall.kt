package com.example.namebattler.data.actionData.attackMagic

import com.example.namebattler.data.actionData.ActionInterface
import com.example.namebattler.data.actionData.SkillData
import com.example.namebattler.data.battleData.CharacterInformationHolder
import com.example.namebattler.util.ConditionEnum

class FireBall : ActionInterface {
    override fun getSkillData(character: CharacterInformationHolder): SkillData {
        return activeAction(character)
    }
    override fun activeAction(character: CharacterInformationHolder): SkillData {
        //魔力
        //buff,deBuffの適用
        val correctionLuckVale = character.effectTimeOfLuck
        var luck = character.luck
        if (correctionLuckVale > 0) {
            luck = luck * 3 / 2
        } else if (correctionLuckVale < 0) {
            luck = luck * 2 / 3
        }
        val mp = (character.maxMp + (0..luck).random()) / 2

        //命中基準値
        val hitRateStandard = getPercent(luck)
        //命中率（低目有利）
        val hitRate = (0..100).random()
        //命中値（攻撃補正値）
        val hittingPoint: Int

        //クリティカル成否
        var isCritical = false

        //クリティカルヒット
        if (hitRate <= hitRateStandard / 10) {
            isCritical = true
            hittingPoint = mp / 2
        } else {
            hittingPoint = (100 - hitRate) / 100 * hitRateStandard
        }

        //ダメージ値
        val attackPoint = mp + hittingPoint

        return SkillData(
            "火球の呪文を唱えた",
            attackPoint,
            30,
            isCritical,
            Pair(ConditionEnum.SCALD.text, 2)
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