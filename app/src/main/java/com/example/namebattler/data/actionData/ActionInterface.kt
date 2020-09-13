package com.example.namebattler.data.actionData

import com.example.namebattler.data.battleData.CharacterInformationHolder

interface ActionInterface {
    fun getSkillData(character: CharacterInformationHolder): SkillData

    fun activeAction(character: CharacterInformationHolder): SkillData
    fun passiveDefense(character: CharacterInformationHolder): SkillData
    fun guardAction(): SkillData

    fun getPercent(num: Int?): Int
    fun getPercent(num: Int?, standardValue: Int): Int
}