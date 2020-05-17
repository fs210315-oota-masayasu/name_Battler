package com.example.namebattler.data.characterData

import java.io.Serializable

data class CharacterHolder(
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
    companion object {
        val EXTRA_DATA = "EXTRA_DATA"
    }
}
