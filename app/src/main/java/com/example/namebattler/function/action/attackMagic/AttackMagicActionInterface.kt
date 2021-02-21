package com.example.namebattler.function.action.attackMagic

import com.example.namebattler.function.action.ActionHolder
import com.example.namebattler.function.battle.CharacterInformationHolder

interface AttackMagicActionInterface {
    fun getSkillData(character: CharacterInformationHolder): ActionHolder

    fun activeAction(character: CharacterInformationHolder): ActionHolder

    fun getPercent(num: Int?): Int
    fun getPercent(num: Int?, standardValue: Int): Int
}