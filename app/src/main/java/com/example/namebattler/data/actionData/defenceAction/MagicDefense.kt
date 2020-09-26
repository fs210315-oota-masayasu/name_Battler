package com.example.namebattler.data.actionData.defenceAction

import com.example.namebattler.data.actionData.ActionHolder
import com.example.namebattler.data.battleData.CharacterInformationHolder

class MagicDefense :DefenceActionInterface {
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

        //防護点
        var magicDefence = (def * 2 / 3) + (mp / 3)

        return ActionHolder("攻撃を防いだ", magicDefence, 0, false, Pair("", 0))
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