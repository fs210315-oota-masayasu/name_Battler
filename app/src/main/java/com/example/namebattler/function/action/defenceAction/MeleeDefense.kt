package com.example.namebattler.function.action.defenceAction

import com.example.namebattler.function.action.ActionHolder
import com.example.namebattler.function.battle.CharacterInformationHolder

class MeleeDefense : DefenceActionInterface {
    override fun getSkillData(character: CharacterInformationHolder): ActionHolder {
        return passiveDefense(character)
    }

    override fun passiveDefense(character: CharacterInformationHolder): ActionHolder {
        //防御力
        //buff,deBuffの適用
        val correctionDefVale = character.effectTimeOfDef
        var def = character.def
        if (correctionDefVale > 0) {
            def = def * 3 / 2
        } else if (correctionDefVale < 0) {
            def = def * 2 / 3
        }

        //筋力
        //buff,deBuffの適用
        val correctionStrValue = character.effectTimeOfStr
        var str = character.str

        if (correctionStrValue > 0) {
            str = str * 3 / 2
        } else if (correctionStrValue < 0) {
            str = str * 2 / 3
        }

        //防護点
        val meleeDefensePoint = (def * 2 / 3) + (str / 3)

        return ActionHolder(
            "攻撃を防いだ",
            meleeDefensePoint,
            0,
            false,
            Pair("", 0)
        )
    }

    override fun guardAction(): ActionHolder {
        return ActionHolder()
    }

    override fun getPercent(num: Int?): Int {
        return num!! * 100 / 255
    }

    override fun getPercent(num: Int?, standardValue: Int): Int {
        return num!! * 100 / standardValue
    }
}