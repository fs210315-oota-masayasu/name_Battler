package com.example.namebattler.data

import java.io.Serializable

data class CharaState(
    val name : String,
    val job : String
): Serializable{
    companion object {
        val EXTRA_DATA = "EXTRA_DATA"
    }
}
