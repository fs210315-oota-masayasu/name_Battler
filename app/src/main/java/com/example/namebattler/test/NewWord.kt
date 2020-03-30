package com.example.namebattler.test

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "new_word_table")
class NewWord (
    @PrimaryKey @ColumnInfo(name = "word")
    val word: String,

    @ColumnInfo(name = "id")
    val id : Int
)