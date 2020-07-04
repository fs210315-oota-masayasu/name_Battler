package com.example.namebattler.data.battleData

import java.io.Serializable

data class InformationHolder (
    var firstEnemyName :String,
    var firstEnemyMaxHp :String,
    var firstEnemyMaxMp :String,
    var firstEnemyCurrentHp :String,
    var firstEnemyCurrentMp :String,
    var firstEnemyCond :String,

    var secondEnemyName :String,
    var secondEnemyMaxHp :String,
    var secondEnemyMaxMp :String,
    var secondEnemyCurrentHp :String,
    var secondEnemyCurrentMp :String,
    var secondEnemyCond :String,

    var thirdEnemyName :String,
    var thirdEnemyMaxHp :String,
    var thirdEnemyMaxMp :String,
    var thirdEnemyCurrentHp :String,
    var thirdEnemyCurrentMp :String,
    var thirdEnemyCond :String,

    var firstPartyName :String,
    var firstPartyMaxHp :String,
    var firstPartyMaxMp :String,
    var firstPartyCurrentHp :String,
    var firstPartyCurrentMp :String,
    var firstPartyCond :String,

    var secondPartyName :String,
    var secondPartyMaxHp :String,
    var secondPartyMaxMp :String,
    var secondPartyCurrentHp :String,
    var secondPartyCurrentMp :String,
    var secondPartyCond :String,

    var thirdPartyName :String,
    var thirdPartyMaxHp :String,
    var thirdPartyMaxMp:String,
    var thirdPartyCurrentHp :String,
    var thirdPartyCurrentMp :String,
    var thirdPartyCond :String
): Serializable {
    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
    }
}