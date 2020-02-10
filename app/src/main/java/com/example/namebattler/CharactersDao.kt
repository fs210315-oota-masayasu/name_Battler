package com.example.namebattler

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


/* リファレンス
    // メソッドの引数をSQLのパラメーターにマッピングするには :引数名 と書く
    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllaByIds(vararg userIds: Int): List<User>

    // 複数の引数も渡せる
    @Query("SELECT * FROM user WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    // データモデルのクラスを引数に渡すことで、データの作成ができる。
    @Insert
    fun insert(user: User)

    // 可変長引数にしたり
    @Insert
    fun insertAll(vararg users: User)

    // Listで渡したりもできる
    @Insert
    fun insertAll(users: List<User>)

    // データモデルのクラスを引数に渡すことで、データの削除ができる。主キーでデータを検索して削除する場合。
    @Delete
    fun delete(user: User)

    // 複雑な条件で削除したい場合は、@Queryを使ってSQLを書く
    @Query("DELETE FROM user WHERE age < :age")
    fun deleteYoungerThan(age: Int)
*/
}