package com.example.exam2_3

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent = Intent(this, MyService::class.java)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            startForegroundService(intent)
        }
        else
        {
            startService(intent)
        }

        button.setOnClickListener { view ->
            stopService(intent)
        }

        button2.setOnClickListener { view ->
            startService(intent)
        }
    }
}
