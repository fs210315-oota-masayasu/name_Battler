package com.example.namebattler.data.actionData.supportMagic

import com.example.namebattler.data.actionData.ActionInterface
import com.example.namebattler.data.actionData.SkillData
import com.example.namebattler.data.battleData.CharacterInformationHolder

class CureWounds : ActionInterface {
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
        val healStandard = getPercent(luck)
        //回復量
        var healPoint = (healStandard .. healStandard * 2).random()

        return SkillData(
            "癒しの呪文を唱えた",
            healPoint,
            20,
            false,
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