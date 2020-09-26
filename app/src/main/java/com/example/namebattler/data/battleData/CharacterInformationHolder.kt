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

): Serializable {

    constructor():this(0,"","","",0,0,0,0,0,0,0,0,0,0,0,0, mutableMapOf())
    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
    }
}