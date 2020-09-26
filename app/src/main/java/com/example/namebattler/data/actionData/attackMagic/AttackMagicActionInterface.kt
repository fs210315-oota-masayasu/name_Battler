package com.example.namebattler.data.actionData.attackMagic

import com.example.namebattler.data.actionData.ActionHolder
import com.example.namebattler.data.battleData.CharacterInformationHolder

interface AttackMagicActionInterface {
    fun getSkillData(character: CharacterInformationHolder): ActionHolder

    fun activeAction(character: CharacterInformationHolder): ActionHolder

    fun getPercent(num: Int?): Int
    fun getPercent(num: Int?, standardValue: Int): Int
}