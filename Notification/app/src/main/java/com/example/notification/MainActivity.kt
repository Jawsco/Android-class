package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
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

        button.setOnClickListener { view ->
            var builder = getNotificationBuilder("channel1", "첫 번째 채널")
            builder.setSmallIcon(android.R.drawable.ic_menu_search)
            builder.setAutoCancel(true)
            builder.setContentTitle("Content Title 1")
            builder.setContentText("Context Text 1")
            var notication = builder.build()
            var mng = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mng.notify(10, notication)
        }

        button2.setOnClickListener { view ->
            var builder = getNotificationBuilder("channel2", "두 번째 채널")
            builder.setSmallIcon(android.R.drawable.ic_menu_search)
            builder.setAutoCancel(true)
            builder.setContentTitle("Content Title 2")
            builder.setContentText("Context Text 2")
            var notication = builder.build()
            var mng = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mng.notify(20, notication)
        }

        button3.setOnClickListener { view ->
            var builder = getNotificationBuilder("channel3", "세번째 채널")
            builder.setSmallIcon(android.R.drawable.ic_menu_search)
            builder.setAutoCancel(true)
            builder.setContentTitle("Content Title 2")
            builder.setContentText("Context Text 2")
            var notication = builder.build()
            var mng = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mng.notify(30, notication)
        }
    }
}
