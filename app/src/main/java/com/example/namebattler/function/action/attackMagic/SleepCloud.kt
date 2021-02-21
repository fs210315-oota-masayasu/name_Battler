package com.example.namebattler.function.action.attackMagic

import com.example.namebattler.function.action.ActionHolder
import com.example.namebattler.function.battle.CharacterInformationHolder
import com.example.namebattler.function.ConditionEnum

class SleepCloud : AttackMagicActionInterface {
    override fun getSkillData(character: CharacterInformationHolder): ActionHolder {
        return activeAction(character)
    }

    override fun activeAction(character: CharacterInformationHolder): ActionHolder {
        return ActionHolder(
            "眠りの呪文を唱えた",
            0,
            10,
            false,
            Pair(ConditionEnum.SLEEP.text, 2)
        )
    }

    override fun getPercent(num: Int?): Int {
        return num!! * 100 / 255
    }

    override fun getPercent(num: Int?, standardValue: Int): Int {
        return num!! * 100 / standardValue
    }
}