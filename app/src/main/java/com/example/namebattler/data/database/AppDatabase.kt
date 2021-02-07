package com.example.namebattler.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Characters::class], version = 3, exportSchema = true)
public abstract class AppDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {

/*
                    //初期登録例（最初にDB内のクリアしないと起動時に重複して登録される）
                    var characterDao = database.charactersDao()
                    //クリア処理
                    characterDao.deleteAll()

                    var accessCheckCharaData =
                        Characters(
                            "せんし",
                            0,
                            50,
                            50,
                            50,
                            50,
                            50,
                            50,
                            1588085823101
                        )
                    characterDao.insert(accessCheckCharaData)


                    accessCheckCharaData =
                        Characters(
                            "白魔術師",
                            0,
                            80,
                            80,
                            80,
                            80,
                            80,
                            80,
                            1588085823101
                        )
                    characterDao.insert(accessCheckCharaData)

                    accessCheckCharaData =
                        Characters(
                            "おおお",
                            0,
                            30,
                            30,
                            30,
                            30,
                            30,
                            30,
                            1588085823101
                        )
                    characterDao.insert(accessCheckCharaData)
                    */
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private const val dbName = "CHARACTERS"
        fun getInstance(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {
            return INSTANCE
                ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    dbName
                ).addCallback(
                    AppDatabaseCallback(
                        scope
                    )
                )
                    .fallbackToDestructiveMigration() //schema version間の移行パス欠落時に破壊的再作成を行う
                    .build()
                INSTANCE = instance

                // return instance
                instance
            }
        }
    }
}