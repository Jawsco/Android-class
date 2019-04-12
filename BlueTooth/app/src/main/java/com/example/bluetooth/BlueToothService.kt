package com.example.bluetooth

import android.app.Service
import android.content.Intent
import android.os.IBinder

class BlueToothService : Service()
{
    private val tag : String = "BluetoothService"
    override fun onBind(intent: Intent): IBinder
    {
        TODO("Return the communication channel to the service.")
    }
}
