package com.example.kotlin_app2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var data1 = intent.getIntExtra("data1", 0)
        textView2.text = "data1 : ${data1}\n"
        button2.setOnClickListener { view ->
            var intent2 = Intent()
            intent2.putExtra("value1", 200)
            setResult(RESULT_OK, intent2)
            finish()
        }
    }
}
