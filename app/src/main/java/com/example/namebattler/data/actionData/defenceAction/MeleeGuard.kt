package com.example.namebattler.data.actionData.defenceAction

import com.example.namebattler.data.actionData.ActionInterface
import com.example.namebattler.data.actionData.SkillData
import com.example.namebattler.data.battleData.CharacterInformationHolder

class MeleeGuard : ActionInterface {
    override fun getSkillData(character: CharacterInformationHolder): SkillData {
        return guardAction()
    }

    override fun activeAction(character: CharacterInformationHolder): SkillData {
        return SkillData()
    }

    override fun passiveDefense(character: CharacterInformationHolder): SkillData {
        return SkillData()
    }

    override fun guardAction(): SkillData {
        return SkillData("防御の構えをとった", 2, 0, false, Pair("", 0))
    }

    override fun getPercent(num: Int?): Int {
        return num!! * 100 / 255
    }

    override fun getPercent(num: Int?, standardValue: Int): Int {
        return num!! * 100 / standardValue
    }
}