package com.example.headsupgame

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context) : SQLiteOpenHelper(context, "celebrities.db", null, 1) {

    private var sqLiteDatabase : SQLiteDatabase = writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table celebritiesDetails (Name text , Taboo1 text , Taboo2 text , Taboo3 text)")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) { }

    fun savedat(s1: String, s2: String , s3: String, s4: String): Long {

        val cv = ContentValues()
        cv.put("Name", s1)
        cv.put("Taboo1", s2)
        cv.put("Taboo2", s3)
        cv.put("Taboo3", s4)
        return sqLiteDatabase.insert("celebritiesDetails", null, cv)
    }
}