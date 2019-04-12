package com.example.helpme

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.activity_main4.*
import java.io.InputStream
import java.net.URL

class Main4Activity : AppCompatActivity()
{
    var idx : Int = 0
    var value : Int = 0
    var isRunning : Boolean = false
    var temp : Int = 0
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        button9.setOnClickListener { view ->
            if(temp == 0)
            {
                temp++
                ThreadClass().start()
                isRunning = true
            }
            else
            {
                isRunning = !isRunning
            }

        }

        button10.setOnClickListener { view ->
            textView.append("${idx + 1} ")
        }

        button11.setOnClickListener { view ->
            finish()
        }
    }

    inner class ThreadClass : Thread()
    {
        override fun run()
        {
            while(true)
            {
                while(isRunning)
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
                            resourceId = resources.getIdentifier("i", "drawable", packageName)
                    }
                    runOnUiThread{
                        imageView2.setImageResource(resourceId!!)
                    }
                    SystemClock.sleep(100)
                    if(idx == 9)
                    {
                        idx = 0
                    }
                }
            }
        }
    }

    override fun onDestroy()
    {
        super.onDestroy()
    }
}
