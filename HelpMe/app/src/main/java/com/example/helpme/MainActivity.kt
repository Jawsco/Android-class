package com.example.helpme

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
            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener { view ->
            val intent = Intent(this, Main3Activity::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener { view ->
            val intent = Intent(this, Main4Activity::class.java)
            startActivity(intent)
        }

        button4.setOnClickListener { view ->
            val intent = Intent(this, Main5Activity::class.java)
            startActivity(intent)
        }
    }
}
