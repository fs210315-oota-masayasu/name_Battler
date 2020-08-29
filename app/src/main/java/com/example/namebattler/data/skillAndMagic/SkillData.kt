package com.example.namebattler.data.skillAndMagic

data class SkillData (
    val flavorText: String,
    val resultPoint: Int,
    val costMp: Int,
    val isCritical: Boolean,
    val giveCond: Pair<String, Int>
){
}