package com.example.thread

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    var isRunning : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener { view ->
            var now = System.currentTimeMillis()
            textView.text = "버튼 클릭 : ${now}"
        }
        while(true)//무한루프 발생시 UI 변경 X
        {
            var now = System.currentTimeMillis()
            textView2.text = "무한 루프 : ${now}"
        }

        isRunning = true
        var thread = ThreadClass1()
        thread.start()
    }
    inner class ThreadClass1 : Thread()
    {
        override fun run()
        {
            while(isRunning) {
                SystemClock.sleep(1000)
                var now = System.currentTimeMillis()
                Log.d("test1", "쓰레드 : ${now}")
                textView2.text = "쓰레드 : ${now}"
            }
        }

    }
}
