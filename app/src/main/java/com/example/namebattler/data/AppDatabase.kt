package com.example.namebattler.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Characters::class), version = 1)
abstract  class AppDatabase : RoomDatabase() {
    // DAOを取得する。
    abstract fun charactersDao(): CharactersDao

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var characterDao = database.charactersDao()
                    var accsessCheckCharaData =
                        Characters("testName", 1, 100, 100, 100, 100, 100, 100, 20000101)

                    characterDao.insert(accsessCheckCharaData)
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
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    dbName
                ).addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance

                // return instance
                instance
            }
        }
    }
}




/*            val tempInstance = INSTANCE
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
}*/