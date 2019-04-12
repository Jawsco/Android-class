package com.example.parcelable

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    var SECOND_ACTIVITY = 1
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{ view ->
            var t = TestClass()
            t.name = editText.text.toString()
            t.age = editText2.text.toString().toInt()
            t.address = editText3.text.toString()
            t.phone_number = editText4.text.toString()

            var intent = Intent(this, Main2Activity::class.java)
            intent.putExtra("test", t)
            startActivityForResult(intent, SECOND_ACTIVITY)
        }
    }
}
