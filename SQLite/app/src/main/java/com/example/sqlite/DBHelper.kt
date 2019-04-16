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

        var sql = "create table Student (" +
                "idx integer primary key autoincrement, " +
                "name text not null, " +
                "age integer not null, " +
                "dateData date not null" +
                ")"

        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int)
    {
        Log.d("msg", "oldVersion : ${p1}, newVersion : ${p2}")
    }
}