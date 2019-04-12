package com.example.helpme

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main3.*
import java.io.InputStream
import java.net.URL

class Main3Activity : AppCompatActivity()
{
    var idx : Int = 0
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        button7.setOnClickListener { view ->
            ThreadClass().start()
        }

        button8.setOnClickListener { view ->
            finish()
        }
    }

    inner class ThreadClass : Thread()
    {
        override fun run()
        {
            var resourceId : Int? = null
            when(idx++)
            {
                0->
                    resourceId = resources.getIdentifier("a", "drawable", packageName)
                1->
                    resourceId = resources.getIdentifier("b", "drawable", packageName)
                2->
                    resourceId = resources.getIdentifier("c", "drawable", packageName)
                3->
                    resourceId = resources.getIdentifier("d", "drawable", packageName)
                4->
                    resourceId = resources.getIdentifier("e", "drawable", packageName)
                5->
                    resourceId = resources.getIdentifier("f", "drawable", packageName)
                6->
                    resourceId = resources.getIdentifier("g", "drawable", packageName)
                7->
                    resourceId = resources.getIdentifier("h", "drawable", packageName)
                8->
                {
                    resourceId = resources.getIdentifier("i", "drawable", packageName)
                    idx = 0
                }
            }
            runOnUiThread{
                imageView.setImageResource(resourceId!!)
            }
        }
    }

    override fun onDestroy()
    {
        super.onDestroy()
    }
}
