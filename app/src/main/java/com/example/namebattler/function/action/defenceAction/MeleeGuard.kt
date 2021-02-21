package com.example.namebattler.function.action.defenceAction

import com.example.namebattler.function.action.ActionHolder
import com.example.namebattler.function.battle.CharacterInformationHolder

class MeleeGuard : DefenceActionInterface {
    override fun getSkillData(character: CharacterInformationHolder): ActionHolder {
        return guardAction()
    }

    override fun passiveDefense(character: CharacterInformationHolder): ActionHolder {
        return ActionHolder()
    }

    override fun guardAction(): ActionHolder {
        return ActionHolder("防御の構えをとった", 2, 0, false, Pair("", 0))
    }

    override fun getPercent(num: Int?): Int {
        return num!! * 100 / 255
    }

    override fun getPercent(num: Int?, standardValue: Int): Int {
        return num!! * 100 / standardValue
    }
}