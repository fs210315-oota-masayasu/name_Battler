package com.example.namebattler.data.jobData

class SpellCaster:JobManager.JobInterface() {
    override fun generateParam(): MutableList<Int> {
        val hp = 201
        val mp = 202
        val str = 203
        val def = 204
        val agi = 205
        val luck = 206
        return mutableListOf(hp,mp,str,def,agi,luck)
    }

    override fun testMethod():Int{
        return 200
    }
}