package com.example.namebattler.data.characterData

import com.example.namebattler.data.database.Characters
import com.example.namebattler.data.database.DateConverter
import com.example.namebattler.data.jobData.JobManager

class Player(private val name: String, private val job: Int){

    private var characters : Characters? = null

    //作成日時
    private val currentDate = DateConverter()
        .getCurrentDate()

    private fun setParam(){

        val param: MutableList<Int> = JobManager().paramByJob(job)

        val hp = 0 + param[0]
        val mp = 0 + param[1]
        val str = 0 + param[2]
        val def = 0 + param[3]
        val agi = 0 + param[4]
        val luck = 0 + param[5]

        characters = Characters(
            name,
            job,
            hp,
            mp,
            str,
            def,
            agi,
            luck,
            currentDate
        )
    }

    fun getParam() : Characters?{
        setParam()
        return this.characters
    }


}