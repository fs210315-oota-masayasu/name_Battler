package com.example.namebattler.data

class JobList {

    // TODO リスト化
    /*
        "戦士"         ->  0
        "魔法使い"     ->  1
        "僧侶"         ->  2
        "バーサーカー" ->  3
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
}