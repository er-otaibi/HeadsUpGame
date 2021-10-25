package com.example.headsupgame

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
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

    @SuppressLint("Range")
    fun getNote(note: String): Celebrities {

        var c : Cursor = sqLiteDatabase.query("celebritiesDetails" , null , "Note=?" , arrayOf(note) , null , null , null)
        c.moveToFirst()

       var name = c.getString(c.getColumnIndex("Name"))
       var t1 = c.getString(c.getColumnIndex("Taboo1"))
       var t2 =  c.getString(c.getColumnIndex("Taboo2"))
       var t3 =  c.getString(c.getColumnIndex("Taboo3"))

        return Celebrities(name,t1,t2,t3)

    }

    @SuppressLint("Range")
    fun readData() {
        var selectQuery = "SELECT  * FROM celebritiesDetails"
        var cursor: Cursor? = null
        try {

            cursor = sqLiteDatabase.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            sqLiteDatabase.execSQL(selectQuery)
        }
        var name: String
        var t1: String
        var t2: String
        var t3: String
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    name = cursor.getString(cursor.getColumnIndex("Name"))
                    t1 = cursor.getString(cursor.getColumnIndex("Taboo1"))
                    t2 = cursor.getString(cursor.getColumnIndex("Taboo2"))
                    t3 = cursor.getString(cursor.getColumnIndex("Taboo3"))
                    MyList.mylist.add(Celebrities(name,t1,t2,t3))
                } while (cursor.moveToNext())
            }
        }
    }
}