package com.example.namebattler.data.actionData.defenceAction

import com.example.namebattler.data.actionData.ActionHolder
import com.example.namebattler.data.battleData.CharacterInformationHolder

interface DefenceActionInterface {
    fun getSkillData(character: CharacterInformationHolder): ActionHolder

    fun passiveDefense(character: CharacterInformationHolder): ActionHolder
    fun guardAction(): ActionHolder

    fun getPercent(num: Int?): Int
    fun getPercent(num: Int?, standardValue: Int): Int
}