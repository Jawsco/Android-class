package com.example.subject2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        textView3.text = intent.getLongExtra("data", 100).toString()

        button4.setOnClickListener { view ->
            finish()
        }
    }
}
