package com.example.namebattler.data

class Jobs {

    //private var name: String? = null

    fun convertJobnameToValue(name : String): Int {
        var resultValue = 0

        when(name){
            "戦士" -> resultValue = 1
            "魔法使い" -> resultValue = 2
            "僧侶" -> resultValue = 3
            "バーサーカー" -> resultValue = 4
        }

        return resultValue
    }
}