package com.example.namebattler

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper

private const val DB_NAME = "namebattler.sqlite3"

class SelectSql(context : Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION)
{


}