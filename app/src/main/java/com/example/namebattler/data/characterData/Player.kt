package com.example.namebattler.data.characterData

import com.example.namebattler.data.database.Characters
import com.example.namebattler.data.database.DateConverter
import com.example.namebattler.data.jobData.JobManager
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.math.ceil

class Player(private val name: String, private val job: Int){

    private var characters : Characters? = null

    private var hp : Int = 0
    private var mp : Int = 0
    private var str : Int = 0
    private var def : Int = 0
    private var agi : Int = 0
    private var luck : Int = 0

    //作成日時
    private val currentDate = DateConverter()
        .getCurrentDate()

    //getNumber()で生成した乱数と各ジョブの補正値を合計してCharactersオブジェクトへ格納する
    private fun setParam(){

        val jobObj = JobManager()

       jobObj.addParam(job)

        this.hp = getNumber(name,0) + jobObj.hp
        this.mp = getNumber(name,1) + jobObj.mp
        this.str= getNumber(name,2) + jobObj.str
        this.def= getNumber(name,3) + jobObj.def
        this.agi= getNumber(name,4) + jobObj.agi
        this.luck= getNumber(name,5) + jobObj.luck

        characters = Characters(
            name,
            job,
            this.hp,
            this.mp,
            this.str,
            this.def,
            this.agi,
            this.luck,
            this.currentDate
        )
    }

    //setParam()を実行して格納したCharactersを返す
    fun getParam() : Characters?{
        setParam()
        return this.characters
    }

    /**
     *  名前からハッシュ値を生成し、指定された位置の数値を取り出す
     *	@param name : 名前
     *	@param index : 何番目の数値を取り出すか
     *	@return 数値(0~255)
     */
    private fun getNumber(name: String, index: Int): Int {
        try { // 名前からハッシュ値を生成する
            val result: ByteArray =
                MessageDigest.getInstance("SHA-1").digest(name.toByteArray())
            val digest = java.lang.String.format("%040x", BigInteger(1, result))
            // ハッシュ値から指定された位置の文字列を取り出す（２文字分）
            val hex = digest.substring(index * 2, index * 2 + 2)
            // 取り出した文字列（16進数）を数値に変換する
            return hex.toInt(16)
        } catch (e: Exception) { // エラー
            e.printStackTrace()
        }
        return 0
    }

    fun getPercent(num:Int?):Int{
        val numOfFloat : Double = (num?:0 / 255)*100.toDouble()
        return ceil(numOfFloat).toInt()
    }


}