package com.example.namebattler.database.characterDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CharactersDao {

    // シンプルなSELECTクエリ
    @Query("SELECT * FROM CHARACTERS")
    fun getAllCharacters(): LiveData<List<Characters>>

    @Query("SELECT * FROM CHARACTERS WHERE name > :searchName")
    fun getCharacterAtName(searchName :String):LiveData<List<Characters>>

    //登録件数取得
    @Query("SELECT COUNT(*) FROM CHARACTERS")
    fun numOfRegistrations():Int

    //nameが重複しているレコード数を取得する
    @Query("SELECT COUNT(*) FROM CHARACTERS WHERE name = :searchName")
    fun countOverlap(searchName : String): Int


    //アップデート処理
    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(characters: Characters)

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
    suspend fun delete(characters: Characters)

    @Query("DELETE FROM CHARACTERS")
    suspend fun deleteAll()
}