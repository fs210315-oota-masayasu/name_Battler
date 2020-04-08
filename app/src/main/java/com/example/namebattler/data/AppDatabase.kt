package com.example.namebattler.data

import android.content.Context
import android.util.Log
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
                    var characterDao = database.charactersDao()

                    //クリア処理
                    characterDao.deleteAll()

                    var accessCheckCharaData = Characters("default_name",1,10,10,10,10,10,10,10)
                    characterDao.insert(accessCheckCharaData)

                    accessCheckCharaData = Characters("mmmmasayasu",2,20,20,20,20,20,20,20)
                    characterDao.insert(accessCheckCharaData)

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
            Log.d("tag", "databaseの動作確認")

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    dbName
                ).addCallback(AppDatabaseCallback(scope))
                    .fallbackToDestructiveMigration() //schema version間の移行パス欠落時に破壊的再作成を行う
                    .build()
                INSTANCE = instance

                // return instance
                instance
            }
        }
    }
}