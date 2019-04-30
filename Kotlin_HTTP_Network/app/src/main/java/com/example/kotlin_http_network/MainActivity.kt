package com.example.kotlin_http_network

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->
            var thread = NetworkThread()
            thread.start()
        }
    }

    inner class NetworkThread : Thread()
    {
        override fun run()
        {
//            var site = "https://google.com"
            var site = "http://10.80.161.179:8080/HttpNetwork/string.jsp"
            var url = URL(site)
            var connect = url.openConnection()
            var input = connect.getInputStream()

            var isr = InputStreamReader(input, "UTF-8")
            var br = BufferedReader(isr)

            var str:String? = null
            var buf = StringBuffer()

            do
            {
                str = br.readLine()
                if(str != null)
                {
                    buf.append(str)
                }
            }while (str != null)
            var data = buf.toString()
            runOnUiThread{
                textView.text = data
                webView.loadUrl(site)
            }
        }
    }
}
