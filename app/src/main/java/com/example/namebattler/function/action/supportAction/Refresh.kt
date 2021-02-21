package com.example.namebattler.function.action.supportAction

import com.example.namebattler.function.action.ActionHolder
import com.example.namebattler.function.battle.CharacterInformationHolder
import com.example.namebattler.function.ConditionEnum

class Refresh : SupportActionInterface {
    override fun getSkillData(character: CharacterInformationHolder): ActionHolder {
        return activeAction(character)
    }

    override fun activeAction(character: CharacterInformationHolder): ActionHolder {
        return ActionHolder(
            "リフレッシュの呪文を唱えた",
            0,
            10,
            false,
            Pair(ConditionEnum.FRESH.text, 0)
        )
    }

    override fun getPercent(num: Int?): Int {
        return num!! * 100 / 255
    }

    override fun getPercent(num: Int?, standardValue: Int): Int {
        return num!! * 100 / standardValue
    }
}