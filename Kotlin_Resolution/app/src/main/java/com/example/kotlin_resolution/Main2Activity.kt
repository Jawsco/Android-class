package com.example.kotlin_resolution

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import kotlinx.android.synthetic.main.activity2_main.*
import kotlinx.android.synthetic.main.second_activity.*

class Main2Activity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2_main)

        if(savedInstanceState != null) // 회전할 경우 널이아닌 값이 반환된다
        {
            textView2.text = savedInstanceState?.getString("data3")
        }

        textView2.text = intent.getStringExtra("data2")
    }

    override fun onSaveInstanceState(outState: Bundle?)
    {
        super.onSaveInstanceState(outState)
        outState?.putString("data3", textView2.text.toString())
    }
}
