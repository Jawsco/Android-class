package com.example.startnewactivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.support.v4.content.ContextCompat.startActivity
import android.telephony.SmsMessage
import android.widget.Toast

class MyReceiver : BroadcastReceiver()
{

    override fun onReceive(context: Context, intent: Intent)
    {
        when(intent.action)
        {
            Intent.ACTION_BOOT_COMPLETED ->
            {
                var toast = Toast.makeText(context, "부팅 끝", Toast.LENGTH_LONG)
                toast.show()

                var intent = Intent(context, Main2Activity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(context, intent, null)
            }
            Telephony.Sms.Intents.SMS_RECEIVED_ACTION ->
            {
                var str = ""
                var bundle = intent.extras
                if (bundle != null)
                {
                    var obj = bundle.get("pdus") as Array<Any>
                    var msg = arrayOfNulls<SmsMessage>(obj.size)
                    for (i in obj.indices)
                    {
                        msg[i] = SmsMessage.createFromPdu(obj[i] as ByteArray)
                    }
                    for (i in msg.indices)
                    {
                        str = msg[i]?.originatingAddress + " : " + msg[i]?.messageBody
                        var toast = Toast.makeText(context, str, Toast.LENGTH_SHORT)
                        toast.show()

                    }
                }
            }
        }
    }
}
