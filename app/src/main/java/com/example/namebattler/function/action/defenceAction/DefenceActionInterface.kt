package com.example.namebattler.function.action.defenceAction

import com.example.namebattler.function.action.ActionHolder
import com.example.namebattler.function.battle.CharacterInformationHolder

interface DefenceActionInterface {
    fun getSkillData(character: CharacterInformationHolder): ActionHolder

    fun passiveDefense(character: CharacterInformationHolder): ActionHolder
    fun guardAction(): ActionHolder

    fun getPercent(num: Int?): Int
    fun getPercent(num: Int?, standardValue: Int): Int
}