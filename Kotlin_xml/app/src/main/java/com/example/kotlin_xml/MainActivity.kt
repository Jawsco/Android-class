package com.example.kotlin_xml

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Element
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.text = ""
        button.setOnClickListener{ view ->
            var thread = NetwrokThread()
            thread.start()
        }
    }

    inner class NetwrokThread : Thread()
    {
        override fun run()
        {
            var site = "http://10.80.161.179:8080/HttpNetwork/xml.jsp"
            var url = URL(site)
            var connect = url.openConnection()
            var input = connect.getInputStream()

            var factory = DocumentBuilderFactory.newInstance()
            var builder = factory.newDocumentBuilder()
            var doc = builder.parse(input)
            var root = doc.documentElement

            var item_node_list = root.getElementsByTagName("item")
            for(i in 0 until item_node_list.length)
            {
                var item_element = item_node_list.item(i) as Element
                var data1_node_list = item_element.getElementsByTagName("data1")
                var data1_node = data1_node_list.item(0) as Element
                var data1 = data1_node.textContent

                runOnUiThread {
                    textView.append("data1 : ${data1}\n")
                }
            }
        }
    }
}
