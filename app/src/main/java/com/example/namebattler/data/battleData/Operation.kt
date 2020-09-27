package com.example.namebattler.data.battleData

import com.example.namebattler.util.OperationIdEnum

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
    private val operationList = mutableListOf<String>()
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
        //Enumを取得
        val operationEnum = OperationIdEnum.values()
        //シングルトン用にオブジェクトを生成させる
        val instance = Operation()
    }

    //取得したEnumのtextをリストへ格納
    init {
        operationEnum.forEach {
            operationList.add(it.text)
        }
    }

}