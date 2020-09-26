package com.example.namebattler.data.actionData.supportAction

import com.example.namebattler.data.actionData.ActionHolder
import com.example.namebattler.data.battleData.CharacterInformationHolder
import com.example.namebattler.util.ConditionEnum

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