package com.example.namebattler.data.characterData

import java.io.Serializable

data class CharacterHolder(
    val belong :String,
    val name : String,
    val job : String,
    val hp : Int,
    val mp : Int,
    val str: Int,
    val def : Int,
    val agi : Int,
    val luck : Int,
    val currentDate : Long

): Serializable{
    constructor():this("","","",0,0,0,0,0,0,0)
    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
    }
}

