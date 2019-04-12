package com.example.stylenotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    fun getNotificationBuilder(id: String, name: String): NotificationCompat.Builder {
        var builder: NotificationCompat.Builder? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            var channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            manager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(this, id)
        } else {
            builder = NotificationCompat.Builder(this)
        }
        return builder
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->
            var builder = getNotificationBuilder("Style1", "str1")
            builder.setContentTitle("Big Picture")
            builder.setContentText("BIg Picture Notification")
            builder.setSmallIcon(android.R.drawable.btn_star)

            var intent1 = Intent(this, Main2Activity::class.java)
            var pending1 = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT)
            builder.setContentIntent(pending1)

            var big = NotificationCompat.BigPictureStyle(builder)
            var bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_lucky_you)
            big.bigPicture(bitmap)
            big.setBigContentTitle("Big Content Title")
            big.setSummaryText("Big Summary Text")

            var notification = builder.build()
            var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(10, notification)
        }

        button2.setOnClickListener { view ->
            var builder = getNotificationBuilder("Style2", "str2")
            builder.setContentTitle("InBox Conetent Title")
            builder.setContentText("InBox Summary Text")
            builder.setSmallIcon(android.R.drawable.btn_star)

            var inbox = NotificationCompat.InboxStyle(builder)
            inbox.setSummaryText("Summary Text")
            inbox.addLine("aaaaaaaa")
            inbox.addLine("bbbbbbbb")
            inbox.addLine("cccccccc")
            inbox.addLine("dddddddd")

            var intent2 = Intent(this, Main3Activity::class.java)
            var pending2 = PendingIntent.getActivity(this, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT)
            builder.setContentIntent(pending2)

            var notification = builder.build()
            var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(10, notification)
        }

    }
}
