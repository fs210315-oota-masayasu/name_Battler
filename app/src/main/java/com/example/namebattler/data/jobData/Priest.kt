package com.example.namebattler.data.jobData

class Priest:JobManager.JobInterface(){
    override fun generateParam(): MutableList<Int> {
        val hp = 301
        val mp = 302
        val str = 303
        val def = 304
        val agi = 305
        val luck = 306
        return mutableListOf(hp,mp,str,def,agi,luck)
    }

    override fun testMethod():Int{
        return 300
    }
}