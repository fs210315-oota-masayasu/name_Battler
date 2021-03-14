package com.example.namebattler.function.battle

data class ActionResultHolder(
    var attackMagnification :Int,   //攻撃倍率（ターン数を格納）
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





