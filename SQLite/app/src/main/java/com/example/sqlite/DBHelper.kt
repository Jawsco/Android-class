package com.example.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DBHelper(context : Context) : SQLiteOpenHelper(context, "Test.db", null, 1)
{

    override fun onCreate(p0: SQLiteDatabase?)
    {
        Log.d("msg", "on create")

        var sql = "create table TestTable (" +
                "idx integer primary key autoincrement, " + //id
                "textData text not null, " + //가변형문자열
                "intData integer not null, " + //정수
                "floatData real not null, " + //실수
                "dateData date not null" + //날짜
                ")"

        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int)
    {
        Log.d("msg", "oldVersion : ${p1}, newVersion : ${p2}")
    }
}