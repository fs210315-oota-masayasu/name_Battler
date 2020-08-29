package com.example.namebattler.data.battleData

data class ActionResultHolder(
    var flavorText: MutableList<String>,
    var damageToHp: Int,
    var cureToHp: Int,
    var costToMp: Int,
    var buffToDef: Int,
    var changingCond: Pair<String, Int>,
    var targetId: Int,
    var isMagicDamage: Boolean
)





