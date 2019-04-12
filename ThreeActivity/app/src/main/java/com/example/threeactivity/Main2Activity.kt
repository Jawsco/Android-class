package com.example.threeactivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        button3.setOnClickListener { view ->
            var intent = Intent(this, Main3Activity::class.java)
            startActivity(intent)
        }

        button4.setOnClickListener { view ->
            finish()
        }
    }
}
