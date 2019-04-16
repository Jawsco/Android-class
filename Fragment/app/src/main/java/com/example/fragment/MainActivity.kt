package com.example.fragment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    var first = First_Fragment()
    var second = Second_Fragment()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->
            var tran = supportFragmentManager.beginTransaction()

            tran.replace(R.id.containter, first)
            tran.addToBackStack(null)
            tran.commit()
        }

        button2.setOnClickListener { view ->
            var tran = supportFragmentManager.beginTransaction()

            tran.replace(R.id.containter, second)
            tran.addToBackStack(null)
            tran.commit()
        }
    }
}
