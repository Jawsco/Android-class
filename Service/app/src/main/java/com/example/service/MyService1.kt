package com.example.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock
import android.util.Log

class MyService1 : Service()
{
    var isRunning = true

    override fun onBind(intent: Intent): IBinder
    {
        TODO("Return the communication hannel to the service.") //빼면 실행이 안됨....
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
            var idx = 0
            while(isRunning)
            {
                SystemClock.sleep(1000)
                Log.d("tag1", "Service Running : ${idx++}")
            }
        }
    }

    override fun onDestroy()
    {
        Log.d("test1", "서비스 실행 종료")
        isRunning = false
        super.onDestroy()
    }
}
