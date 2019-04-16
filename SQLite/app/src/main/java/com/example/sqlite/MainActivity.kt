package com.example.sqlite

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->
            /*
            var helper = DBHelper(this)
            var db = helper.writableDatabase
            var sql = "insert into TestTable (textData, intData, floatData, dateData)" +
                    "values (?, ?, ?, ?)"
            var sdf = SimpleDateFormat("yyyy--MM-dd-hh-mm-ss", Locale.getDefault())
            var date = sdf.format(Date())

            var arg1 = arrayOf("문자열1", "100", "11.11", date)
            var arg2 = arrayOf("문자열2", "200", "22.22", date)
            db.execSQL(sql, arg1)
            db.execSQL(sql, arg2)
            db.close()
            */
            var helper = DBHelper(this)
            var db = helper.writableDatabase

            var sdf = SimpleDateFormat("yyyy--MM-dd-hh-mm-ss", Locale.getDefault())
            var date = sdf.format(Date())

            var cv1 = ContentValues()
            cv1.put("name", "조석호")
            cv1.put("age", 19)
            cv1.put("dateData", date)
            db.insert("Student", null, cv1)

            var cv2 = ContentValues()
            cv1.put("name", "조현윤")
            cv1.put("age", 18)
            cv1.put("dateData", date)
            db.insert("Student", null, cv1)

            var cv3 = ContentValues()
            cv1.put("name", "조윤현")
            cv1.put("age", 17)
            cv1.put("dateData", date)
            db.insert("Student", null, cv1)

            textView.text = "저장 완료"
        }

        button2.setOnClickListener { view ->
//            var helper = DBHelper(this)
//            var db:SQLiteDatabase = helper.writableDatabase
//            var sql = "select * from TestTable"
//            var c:Cursor = db.rawQuery(sql, null)
//            textView.text = ""
//            while(c.moveToNext())
//            {
//                var idx_pos = c.getColumnIndex("idx")
//                var textData_pos = c.getColumnIndex("textData")
//                var intData_pos = c.getColumnIndex("intData")
//                var floatData_pos = c.getColumnIndex("floatData")
//                var dateData_pos = c.getColumnIndex("dateData")
//
//                var idx = c.getInt(idx_pos)
//                var textData = c.getString(textData_pos)
//                var intData = c.getInt(intData_pos)
//                var floatData = c.getFloat(floatData_pos)
//                var dateData = c.getString(dateData_pos)
//
//                textView.append("idx : ${idx}\n")
//                textView.append("textData : ${textData}\n")
//                textView.append("intData : ${intData}\n")
//                textView.append("floatData : ${floatData}\n")
//                textView.append("dateData : ${dateData}\n")
//            }

            var helper : DBHelper = DBHelper(this)
            var db : SQLiteDatabase = helper.writableDatabase
            var c = db.query("Student", null, null,
                null, null, null, null)
            textView.text = ""
            while(c.moveToNext())
            {
                var idx_pos = c.getColumnIndex("idx")
                var name_pos = c.getColumnIndex("name")
                var age_pos = c.getColumnIndex("age")
                var dateData_pos = c.getColumnIndex("dateData")

                var idx = c.getInt(idx_pos)
                var nameData = c.getString(name_pos)
                var ageData = c.getInt(age_pos)
                var dateData = c.getString(dateData_pos)

                textView.append("idx : ${idx}\n")
                textView.append("textData : ${nameData}\n")
                textView.append("intData : ${ageData}\n")
                textView.append("dateData : ${dateData}\n")
            }
            db.close()
        }
        button3.setOnClickListener { view ->
            var helper = DBHelper(this)
            var db:SQLiteDatabase = helper.writableDatabase
            var cv = ContentValues()
            cv.put("name", "임꺽정")
            var where = "idx=?"
            var args = arrayOf("1")
            db.update("Student", cv, where, args)
            db.close()
            textView.text = "수정완료"
        }

        button4.setOnClickListener { view ->
            var helper = DBHelper(this)
            var db:SQLiteDatabase = helper.writableDatabase
            var sql = "delete from TestTable where not idx = 0"
            db.execSQL(sql)
            db.close()

        }
    }
}
