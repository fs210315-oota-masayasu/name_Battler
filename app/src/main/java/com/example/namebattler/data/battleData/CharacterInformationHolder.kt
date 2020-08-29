package com.example.namebattler.data.battleData

import java.io.Serializable

/**
 * @param name
 * @param maxHp
 * @param currentHp
 * @param maxMp
 * @param currentMp
 * @param cond
 */
data class CharacterInformationHolder(
    val id :Int,
    val belong :String,
    var name :String,
    val job :String,
    var maxHp :Int,
    var currentHp :Int,
    var maxMp :Int,
    var currentMp :Int,
    val str :Int,
    var effectTimeOfStr :Int,
    val def :Int,
    var effectTimeOfDef :Int,
    val agi :Int,
    var effectTimeOfAgi :Int,
    val luck :Int,
    var effectTimeOfLuck :Int,
    var cond :MutableMap<String,Int>


//    val basicStr : Int,
//    val currentStr: Int,
//    val effectTimeOfStr : Int,
//    val basicDef : Int,
//    val currentDef : Int,
//    val effectTimeOfDef : Int,
//    val basicAgi : Int,
//    val currentAgi : Int,
//    val effectTimeOfAgi : Int,
//    val basicLuck : Int,
//    val currentLuck : Int,
//    val effectTimeOfLuck : Int,

): Serializable {
    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
    }
}