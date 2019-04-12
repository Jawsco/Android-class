package com.example.kotlin_ipc

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.SystemClock
import android.util.Log

class IPCService : Service()
{
    var value : Int = 0
    var thread : ThreadClass? = null
    var binder : IBinder = LocalBinder()
    var isRunning : Boolean = true
    override fun onBind(intent: Intent?): IBinder?
    {
        //외부에서 서비스에 접속하면 반환하는 메서드
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        thread = ThreadClass()
        thread?.start()
        return super.onStartCommand(intent, flags, startId)
    }

    inner class ThreadClass : Thread()
    {
        override fun run()
        {
            while(true)
            {
                if(isRunning)
                SystemClock.sleep(1000)
                Log.d("test1", "value : ${value}")
                value++
            }
        }
    }

    inner class LocalBinder : Binder()
    {
        fun getService() : IPCService
        {
            return this@IPCService
        }

        fun getNumber() : Int?
        {
            return value
        }

        fun startThread()
        {
            isRunning = true
        }

        fun stopThread()
        {
            isRunning = false
        }
    }
}
