package com.example.namebattler.data.battleData

class Operation {

    fun getInstance():Operation{
        return instance
    }

    var operationName :String = ""

    fun get():String{
        //初期化（「ガンガンいこうぜ」をセット）
        if (operationName == ""){
            operationName = getOperationList(0)
        }
        return operationName
    }

    //インデックスがキャラクターテーブルのジョブの値になる
    private val operationList = mutableListOf("ガンガンいこうぜ","いのちだいじに","バッチリがんばれ")
    //職業を追加する場合はaddで

    //名前からインデックスを取得
    fun getOperationList(name : String): Int {
        return operationList.indexOf(name)
    }

    //インデックスから名前を取得
    private fun getOperationList(index : Int):String{
        return operationList[index]
    }

    companion object {
        val instance = Operation()
    }

}