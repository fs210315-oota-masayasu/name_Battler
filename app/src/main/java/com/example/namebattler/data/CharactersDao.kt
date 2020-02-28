package com.example.namebattler.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CharactersDao {

    // シンプルなSELECTクエリ
    @Query("SELECT * FROM CHARACTERS")
    fun getAllCharacters(): LiveData<List<Characters>>

    //データの作成：引数（データモデルのクラス）
    @Insert
    fun insert(characters: Characters)

    // 可変長引数にしたり
    @Insert
    fun insertAll(vararg characters: Characters)

    // Listで渡したりもできる
    @Insert
    fun insertAll(characters: List<Characters>)

    //データの削除
    @Delete
    fun delete(characters: Characters)

}