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
data class OutputInformationHolder(
    var name :String,
    var maxHp :String,
    var currentHp :String,
    var maxMp :String,
    var currentMp :String,
    var cond :String
): Serializable {
    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
    }
}