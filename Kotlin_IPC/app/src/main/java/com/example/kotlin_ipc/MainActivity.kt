package com.example.kotlin_ipc

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    var ipc_service : IPCService? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent = Intent(this, IPCService::class.java)
        if(isServiceRunning("com.example.kotlin_ipc.IPCService") == false)
        {
            startService(intent)
        }
        val bindService = bindService(intent, mConnection, Context.BIND_AUTO_CREATE)

        button.setOnClickListener { view ->
            var value = ipc_service?.LocalBinder()?.getNumber()
            textView.text = "value : ${value}"
        }
        button2.setOnClickListener { view ->
            ipc_service?.LocalBinder()?.stopThread()
        }
        button3.setOnClickListener { view ->
            ipc_service?.LocalBinder()?.startThread()
        }
    }
    override fun onDestroy()
    {
        super.onDestroy()
        unbindService(mConnection)
    }

    fun isServiceRunning(name : String) : Boolean
    {
        var manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for(service : ActivityManager.RunningServiceInfo in manager.getRunningServices(Int.MAX_VALUE))
        {
            if(service.service.className.equals(name))
            {
                return true
            }
        }
        return false
    }

    private val mConnection = object : ServiceConnection
    {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?)
        {
            var binder = service as IPCService.LocalBinder
            ipc_service = binder.getService()
        }

        override fun onServiceDisconnected(name: ComponentName?)
        {
            ipc_service = null
        }
    }
}
