package com.example.namebattler.database.characterDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Characters::class], version = 4, exportSchema = true)
public abstract class CharacterDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {

                    //初期登録例（最初にDB内のクリアしないと起動時に重複して登録される）

                    var characterDao = database.charactersDao()
                    //クリア処理
                    characterDao.deleteAll()

                    var accessCheckCharaData =
                        Characters(
                            "あああ",
                            0,
                            111,
                            112,
                            113,
                            114,
                            115,
                            116,
                            1609470000000
                        )
                    characterDao.insert(accessCheckCharaData)


                    accessCheckCharaData =
                        Characters(
                            "ああああ",
                            0,
                            221,
                            222,
                            223,
                            224,
                            225,
                            226,
                            1609470000000
                        )
                    characterDao.insert(accessCheckCharaData)

                    accessCheckCharaData =
                        Characters(
                            "＊＊＊＊＊",
                            1,
                            331,
                            332,
                            333,
                            334,
                            335,
                            336,
                            1609470000000
                        )
                    characterDao.insert(accessCheckCharaData)

                    accessCheckCharaData =
                        Characters(
                            "漢字　太郎",
                            1,
                            441,
                            442,
                            443,
                            444,
                            445,
                            446,
                            1609470000000
                        )
                    characterDao.insert(accessCheckCharaData)

                    accessCheckCharaData =
                        Characters(
                            "AAA　AAAA",
                            2,
                            551,
                            552,
                            553,
                            554,
                            555,
                            556,
                            1609470000000
                        )
                    characterDao.insert(accessCheckCharaData)


                    accessCheckCharaData =
                        Characters(
                            "111　111111",
                            2,
                            661,
                            662,
                            663,
                            664,
                            665,
                            666,
                            1609470000000
                        )
                    characterDao.insert(accessCheckCharaData)


                    accessCheckCharaData =
                        Characters(
                            "AAAAAAAAAAAAAAAAAAA",
                            3,
                            771,
                            772,
                            773,
                            774,
                            775,
                            776,
                            1609470000000
                        )
                    characterDao.insert(accessCheckCharaData)


                    accessCheckCharaData =
                        Characters(
                            "あいう",
                            3,
                            881,
                            882,
                            883,
                            884,
                            885,
                            886,
                            1609470000000
                        )
                    characterDao.insert(accessCheckCharaData)


                    accessCheckCharaData =
                        Characters(
                            "いうえ",
                            0,
                            991,
                            992,
                            993,
                            994,
                            995,
                            996,
                            1609470000000
                        )
                    characterDao.insert(accessCheckCharaData)


                    accessCheckCharaData =
                        Characters(
                            "うえお",
                            0,
                            101,
                            102,
                            103,
                            104,
                            105,
                            106,
                            1609470000000
                        )
                    characterDao.insert(accessCheckCharaData)

//                    characterDao.deleteAll()
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: CharacterDatabase? = null

        private const val dbName = "CHARACTERS"
        fun getInstance(
            context: Context,
            scope: CoroutineScope
        ): CharacterDatabase {
            return INSTANCE
                ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDatabase::class.java,
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