package com.example.exam2_3

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import java.lang.UnsupportedOperationException

class MyService : Service()
{
    var value = 0
    var isRunning : Boolean = true

    override fun onBind(intent: Intent): IBinder
    {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        var thread = ThreadClass()
        thread.start()
        return super.onStartCommand(intent, flags, startId)
    }

    inner class ThreadClass : Thread()
    {
        override fun run()
        {
            while(isRunning)
            {
                SystemClock.sleep(1000)
                Log.d("Thread", "Service Running : ${value}")
                value++
            }
        }
    }

    override fun onDestroy()
    {
        super.onDestroy()
        isRunning = false
    }
}
