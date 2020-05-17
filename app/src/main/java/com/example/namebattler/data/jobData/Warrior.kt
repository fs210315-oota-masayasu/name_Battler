package com.example.namebattler.data.jobData

class  Warrior:JobManager.JobInterface(){
    override fun generateParam(): MutableList<Int> {
        val hp = 101
        val mp = 102
        val str = 103
        val def = 104
        val agi = 105
        val luck = 106
        return mutableListOf(hp,mp,str,def,agi,luck)
    }

    override fun testMethod():Int{
        return 100
    }


}