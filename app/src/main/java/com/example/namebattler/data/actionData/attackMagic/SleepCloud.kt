package com.example.namebattler.data.actionData.attackMagic

import com.example.namebattler.data.actionData.ActionInterface
import com.example.namebattler.data.actionData.SkillData
import com.example.namebattler.data.battleData.CharacterInformationHolder
import com.example.namebattler.util.ConditionEnum

class SleepCloud : ActionInterface {
    override fun getSkillData(character: CharacterInformationHolder): SkillData {
        return activeAction(character)
    }

    override fun activeAction(character: CharacterInformationHolder): SkillData {
        return SkillData(
            "眠りの呪文を唱えた",
            0,
            10,
            false,
            Pair(ConditionEnum.SLEEP.text, 2)
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