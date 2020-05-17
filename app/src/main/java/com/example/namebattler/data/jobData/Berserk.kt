package com.example.namebattler.data.jobData

class Berserk:JobManager.JobInterface(){
    override fun generateParam(): MutableList<Int> {
        val hp = 401
        val mp = 402
        val str = 403
        val def = 404
        val agi = 405
        val luck = 406
        return mutableListOf(hp,mp,str,def,agi,luck)
    }

    override fun testMethod():Int{
        return 400
    }
}