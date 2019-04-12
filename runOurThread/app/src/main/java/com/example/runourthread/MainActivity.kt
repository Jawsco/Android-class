package com.example.runourthread

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock

class MainActivity : AppCompatActivity()
{
    var isRunning : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isRunning = true
        var thread = ThreadClass()
        thread.start()

    }


    override fun onDestroy()
    {
        super.onDestroy()
    }

    inner class ThreadClass : Thread()
    {
        override fun start()
        {
            while(isRunning)
            {
                SystemClock.currentThreadTimeMillis(1000)
            }
        }
    }
}
