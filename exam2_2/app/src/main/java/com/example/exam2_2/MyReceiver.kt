package com.example.exam2_2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat.getExtras
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import java.text.Format
import java.util.*




class MyReceiver : BroadcastReceiver()
{
    private val TAG = "SMSReceiver"

    override fun onReceive(context: Context, intent: Intent)
    {
        if ("android.provider.Telephony.SMS_RECEIVED" == intent.action)
        {
            val bundle = intent.extras
            val messages = bundle.get("pdus") as Array<Any>
            val smsMessage = arrayOfNulls<SmsMessage>(messages.size)

            for (i in messages.indices)
            {
                smsMessage[i] = SmsMessage.createFromPdu(messages[i] as ByteArray)
            }
            val sender = smsMessage[0]?.getOriginatingAddress()
            val message = smsMessage[0]?.getMessageBody().toString()
            sendToActivity(context, sender, message)
            Log.d(TAG, "SMS Message: " + message)
        }
    }
    fun sendToActivity(context : Context, sender : String?, contents : String)
    {
        var intent = Intent(context, MainActivity::class.java)

        // Flag 설정
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK )
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        // 메세지의 내용을 Extra에 넣어줌
        intent.putExtra("sender", sender)
        intent.putExtra("contents", contents)

        context.startActivity(intent)
    }
}
