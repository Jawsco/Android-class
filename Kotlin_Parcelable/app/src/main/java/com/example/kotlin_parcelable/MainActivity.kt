package com.example.kotlin_parcelable

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    val SECOND_ACTIVITY = 1
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->
            var t1 = TestClass()
            t1.age = 100
            t1.name = "홍길동"
            var intent = Intent(this, SecondActivity::Class.java)
            intent.putExtra("test1", t1)
            startActivityForResult(intent, SECOND_ACTIVITY)
        }
    }
}
