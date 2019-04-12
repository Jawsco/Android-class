package com.example.handlerui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    var isRunning : Boolean = false
    var handler : Handler? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->

        }
    }

    inner class ThreadClass : Thread()
    {
        override fun run()
        {
            var a1 = 10
            var a2 = 20
            while(isRunning)
            {
                SystemClock.sleep(100)
                var time = System.currentTimeMillis()
                Log.d("test1", "쓰레드 : ${time}")
                var msg2 = Message()
                msg2.what = 1
                msg2.arg1 = ++a1
                msg2.arg2 = ++a2
                msg2.obj = "안녕하세요"
                handler?.sendMessage(msg2)
            }
        }
    }

    inner class DisplayHandler : Handler()
    {
        override fun handleMessage(msg: Message?)
        {
            super.handleMessage(msg)
            if(msg?.what == 0)
            {
                textView2.text = "Handler : ${msg?.obj}"
            }
            else if(msg?.what == 1)
            {
                textView2.text = "${msg?.arg1}, ${msg.arg2}, ${msg?.obj}"
            }
        }
    }
}
