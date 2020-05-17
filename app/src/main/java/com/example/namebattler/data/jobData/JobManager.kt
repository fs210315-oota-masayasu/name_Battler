package com.example.namebattler.data.jobData

open class JobManager{
    /*
        "戦士", "Warrior"        ->  0
        "魔法使い","SpellCaster"     ->  1
        "僧侶" ,"Priest"        ->  2
        "バーサーカー","Berserk" ->  3
    */

    private val jobList = mutableListOf("戦士","魔法使い","僧侶","バーサーカー")
    //職業を追加する場合はaddで

    //名前からインデックスを取得
    fun getJobList(name : String): Int {
        return jobList.indexOf(name)
    }

    //インデックスから名前を取得
    fun getJobList(index : Int):String{
        return jobList[index]
    }

    fun paramByJob(job:Int):MutableList<Int>{
        var param : MutableList<Int> = mutableListOf()
        when(job){
            0 -> param = Warrior().generateParam()
            1 -> param = SpellCaster().generateParam()
            2 -> param = Priest().generateParam()
            3 -> param = Berserk().generateParam()
        }
        return param
    }

    abstract class JobInterface{
        abstract fun generateParam(): MutableList<Int>

        abstract fun testMethod(): Int
    }
}