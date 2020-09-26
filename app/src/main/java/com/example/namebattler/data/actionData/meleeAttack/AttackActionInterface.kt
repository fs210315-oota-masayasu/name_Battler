package com.example.namebattler.data.actionData.meleeAttack

import com.example.namebattler.data.actionData.ActionHolder
import com.example.namebattler.data.battleData.CharacterInformationHolder

interface AttackActionInterface {
    fun getSkillData(character: CharacterInformationHolder): ActionHolder

    fun activeAction(character: CharacterInformationHolder): ActionHolder

    fun getPercent(num: Int?): Int
    fun getPercent(num: Int?, standardValue: Int): Int
}