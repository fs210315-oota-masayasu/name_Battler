package com.example.namebattler

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CustomOpenHelper
    (context: Context, databaseName:String, factory: SQLiteDatabase.CursorFactory?, version: Int)
    : SQLiteOpenHelper(context, databaseName, factory, version)
{
    override fun onCreate(database: SQLiteDatabase?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        database?.execSQL("create table if not exists CHARACTERS (NAME char NOT NULL PRIMARY KEY, JOB  int NOT NULL DEFAULT (0), HP int NOT NULL DEFAULT (0), image BLOB)");
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}