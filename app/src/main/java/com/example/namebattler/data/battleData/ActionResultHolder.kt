package com.example.namebattler.data.battleData

data class ActionResultHolder(
    var turnCorrection :Int,
    var flavorText: MutableList<String>,
    var damageToHp: Int,
    var cureToHp: Int,
    var costToMp: Int,
    var buffToDef: Int,
    var changingCond: Pair<String, Int>,
    var targetId: Int,
    var isMagicDamage: Boolean
){
    constructor():this(0,mutableListOf(), 0, 0, 0, 0, Pair("", 0), 0, false)
}





