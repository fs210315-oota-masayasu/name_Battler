package com.example.namebattler.data.actionData.supportAction

import com.example.namebattler.data.actionData.ActionHolder
import com.example.namebattler.data.battleData.CharacterInformationHolder

class CureWounds : SupportActionInterface {
    override fun getSkillData(character: CharacterInformationHolder): ActionHolder {
        return activeAction(character)
    }

    override fun activeAction(character: CharacterInformationHolder): ActionHolder {
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

        return ActionHolder(
            "癒しの呪文を唱えた",
            healPoint,
            20,
            false,
            Pair("", 0)
        )
    }

    override fun getPercent(num: Int?): Int {
        return num!! * 100 / 255
    }

    override fun getPercent(num: Int?, standardValue: Int): Int {
        return num!! * 100 / standardValue
    }
}