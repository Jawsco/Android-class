package com.example.kotlin_broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class TestReceiver : BroadcastReceiver()
{

    override fun onReceive(context: Context, intent: Intent)
    {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        TODO("TestReceiver.onReceive() is not implemented")
        var data1 = intent.getIntExtra("data1", 0)
        var str = "data1 : ${data1}\n"
        var t1 = Toast.makeText(context, str, Toast.LENGTH_SHORT)
        t1.show()
    }
}
