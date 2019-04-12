package com.example.kotlin_broadcastapp2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->
//            var intent = Intent("com.test.brapp1") 암시적 인텐드 8.0 이상 사용 불가
            intent.putExtra("data1", 100)
            intent.setClassName("com.example.kotlin_broadcastreceiver", "com.example.kotlin_broadcastreceiver.TestReceiver")
            sendBroadcast(intent)
        }
    }
}
