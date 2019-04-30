package com.example.kotlin_memo


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context : Context) : SQLiteOpenHelper(context,
    "Test.db", null, 1){

    override fun onCreate(p0: SQLiteDatabase?) {
        Log.d("msg", "on create")

        var sql = "create table Memo (" +
                "idx integer primary key autoincrement, " +
                "textData text not null, " +
                "dateData text not null," +
                "userData text not null" +
                ")"

        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?,
                           oldVersion: Int, newVersion: Int) {
        Log.d("msg", "oldVersion : ${oldVersion}," +
                " newVersion : ${newVersion}")
    }
}



