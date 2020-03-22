package com.example.namebattler.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CharactersDao {

    // シンプルなSELECTクエリ
    @Query("SELECT * FROM CHARACTERS")
    fun getAllCharacters(): LiveData<List<Characters>>

    @Query("SELECT * FROM CHARACTERS WHERE name > :searchToName")
    fun getCharacterAtName(searchToName :String):LiveData<List<Characters>>

    //データの作成：引数（データモデルのクラス）
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend  fun insert(characters: Characters)

    // 可変長引数にしたり
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend  fun insertAll(vararg characters: Characters)

    // Listで渡したりもできる
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend  fun insertAll(characters: List<Characters>)

    //データの削除
    @Delete
    suspend  fun delete(characters: Characters)

}