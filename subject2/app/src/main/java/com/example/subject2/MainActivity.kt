package com.example.subject2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.support.v4.app.NotificationCompat
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    var isRunning : Boolean = false
    var count : Int = 0
    var handler : Handler? = null
    var handler2 : Handler? = null

    fun getNotificationBuilder(id:String, name:String) : NotificationCompat.Builder
    {
        var builder : NotificationCompat.Builder? = null
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            var channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            manager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(this, id)
        }
        else
        {
            builder = NotificationCompat.Builder(this)
        }
        return builder
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler()
        handler2 = Handler()

        button.setOnClickListener { view ->
            var now = System.currentTimeMillis()
            textView.text = "버튼 클릭 : ${now}"
            count++
            if(count == 5)
            {
                count = 0
                var builder = getNotificationBuilder("channel1", "첫 번째 채널")
                builder.setSmallIcon(android.R.drawable.ic_menu_search)
                builder.setAutoCancel(true)
                builder.setContentTitle("Content Title 1")
                builder.setContentText("Context Text 1")

                var intent1 = Intent(this, SecondActivity::class.java)
                intent1.putExtra("data", System.currentTimeMillis())
                var pending1 = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT)
                builder.setContentIntent(pending1)

                var notication = builder.build()
                var mng = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                mng.notify(10, notication)
            }
        }

        button2.setOnClickListener { view ->
            isRunning = false
            count++
            if(count == 5)
            {
                count = 0
            }
        }

        button3.setOnClickListener { view ->
            if (!isRunning)
            {
                isRunning = true
                handler?.postDelayed(ThreadClass1(),1000)
            }

            count++
            if(count == 5)
            {
                count = 0
            }
        }

        isRunning = true
        var thread = ThreadClass1()
        var thread2 = ThreadClass2()
        handler?.postDelayed(thread, 1000)
        handler2?.postDelayed(thread2, 0)

    }

    inner class ThreadClass1 : Thread()
    {
        override fun run()
        {

            var now = System.currentTimeMillis()
            Log.d("Test1", "쓰레드 : ${now}")
            textView2.text = "쓰레드 : ${now}"
            if (isRunning)
            {
                handler?.postDelayed(this, 1000)
            }

        }
    }

    inner class ThreadClass2 : Thread()
    {
        override fun run()
        {

            Log.d("text2", "실행됨")
               var builder = getNotificationBuilder("channel2", "두 번째 채널")
                builder.setSmallIcon(android.R.drawable.ic_menu_search)
                builder.setAutoCancel(true)
                builder.setContentTitle("Content Title 2")
                builder.setContentText("Context Text 2")
                var notication = builder.build()
                var mng = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                mng.notify(20, notication)
           handler2?.postDelayed(this,10000)
        }
    }

    override fun onDestroy()
    {
        super.onDestroy()
    }
}
