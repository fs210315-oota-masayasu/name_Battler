package com.example.namebattler

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Characters::class], version = 1)
abstract  class AppDatabase  : RoomDatabase() {
    // DAOを取得する。
    abstract fun charactersDao(): CharactersDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private const val dbName = "CHARACTERS"


        fun getInstance(context: Context, scope: CoroutineScope): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    dbName
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}