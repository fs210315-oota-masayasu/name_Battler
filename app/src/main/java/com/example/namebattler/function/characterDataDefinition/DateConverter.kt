package com.example.namebattler.function.characterDataDefinition

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class DateConverter {
    /**
     * 現在日時をLong型で取得する
     * 日付をデータベースで管理する場合は、この関数の返り値をINTEGER型のカラムに格納すればOK
     */
    fun getCurrentDate() : Long{
        return System.currentTimeMillis()
    }

    /**
     * Long型の日付を引数にとって日付文字列を返す
     * データベースから取得した値を「yyyy/MM/dd HH:mm:ss」の形の文字列として扱うことができる
     */
    @SuppressLint("SimpleDateFormat")
    fun convertLongToString(dataString : Long):String{
        val date = Date(dataString)
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm")
        return simpleDateFormat.format(date)

    }



}