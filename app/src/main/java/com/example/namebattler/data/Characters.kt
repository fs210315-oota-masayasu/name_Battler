package com.example.namebattler.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO 動作検証 No.1　一つのみのカラムで正常に動作できるようにする...Clear！
// TODO 動作検証 No.2　カラムを増やして正常に動作できるようにする...Clear!

@Entity(tableName = "CHARACTERS")
class Characters (
    @PrimaryKey
    @ColumnInfo(name = "name")
    val NAME : String,

    @ColumnInfo(name = "JOB")
    val JOB: Int
//    val JOB: Int,
//
//    @ColumnInfo(name = "HP")
//    val HP: Int,
//
//    @ColumnInfo(name = "MP")
//    val MP: Int,
//
//    @ColumnInfo(name = "STR")
//    val STR: Int,
//
//    @ColumnInfo(name = "DEF")
//    val DEF: Int,
//
//    @ColumnInfo(name = "AGI")
//    val AGI: Int,
//
//    @ColumnInfo(name = "LUCK")
//    val LUCK: Int,
//
//    @ColumnInfo(name = "CREATE_AT")
//    val CREATE_AT: Long
)