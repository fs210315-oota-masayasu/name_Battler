package com.example.namebattler.data.actionData.defenceAction

import com.example.namebattler.data.actionData.ActionInterface
import com.example.namebattler.data.actionData.SkillData
import com.example.namebattler.data.battleData.CharacterInformationHolder

class MeleeDefense : ActionInterface {
    override fun getSkillData(character: CharacterInformationHolder): SkillData {
        return passiveDefense(character)
    }

    override fun activeAction(character: CharacterInformationHolder): SkillData {
        return SkillData()
    }

    override fun passiveDefense(character: CharacterInformationHolder): SkillData {
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

        return SkillData(
            "攻撃を防いだ",
            meleeDefensePoint,
            0,
            false,
            Pair("", 0)
        )
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