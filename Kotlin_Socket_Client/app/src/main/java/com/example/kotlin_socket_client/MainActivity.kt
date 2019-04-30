package com.example.kotlin_socket_client

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->
            NetworkThread().start()
        }
    }

    inner class NetworkThread : Thread()
    {
        override fun run()
        {
            var data2 = ""
            try
            {
                var socket = Socket("10.80.161.179", 9999)
                var input = socket.getInputStream()
                var ids = DataInputStream(input)
                var dis = DataInputStream(ids)
                var output = socket.getOutputStream()
                var dos = DataOutputStream(output)
                dos.writeUTF("클라이언트가 보낸 문자열")
                data2 = dis.readUTF()
                socket.close()
                runOnUiThread{
                    textView.append("data3 : ${data2}")
                }
            }
            catch (e : Exception)
            {
                e.printStackTrace()
            }
        }
    }
}
