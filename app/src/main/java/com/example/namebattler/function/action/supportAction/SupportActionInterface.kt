package com.example.namebattler.function.action.supportAction

import com.example.namebattler.function.action.ActionHolder
import com.example.namebattler.function.battle.CharacterInformationHolder

interface SupportActionInterface {
    fun getSkillData(character: CharacterInformationHolder): ActionHolder

    fun activeAction(character: CharacterInformationHolder): ActionHolder

    fun getPercent(num: Int?): Int
    fun getPercent(num: Int?, standardValue: Int): Int
}