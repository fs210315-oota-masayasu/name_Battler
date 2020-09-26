package com.example.namebattler.data.actionData

data class ActionHolder (
    val flavorText: String,
    val resultPoint: Int,
    val costMp: Int,
    val isCritical: Boolean,
    val giveCond: Pair<String, Int>
){
    constructor():this("",0,0,false,Pair("",0))
}