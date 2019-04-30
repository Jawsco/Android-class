package com.example.exam2_2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    val permission_list = arrayOf(
        Manifest.permission.READ_SMS,
        Manifest.permission.RECEIVE_SMS
    )

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onNewIntent(this.intent)
        checkPermission()
    }

    override fun onNewIntent(intent: Intent?)
    {
        super.onNewIntent(intent)
        setIntent(intent)
        textView.text = intent?.getStringExtra("sender") + intent?.getStringExtra("contents")
    }

    fun checkPermission()
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            return
        }
        for (permission: String in permission_list)
        {
            var chk = checkCallingOrSelfPermission(permission)
            if (chk == PackageManager.PERMISSION_DENIED)
            {
                requestPermissions(permission_list, 0)
                break
            }
        }
    }
}
