package com.example.namebattler

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Characters::class], version = 1)
abstract  class AppDatabase  : RoomDatabase(){

    // DAOを取得する。
    abstract fun charactersDao(): CharactersDao
}