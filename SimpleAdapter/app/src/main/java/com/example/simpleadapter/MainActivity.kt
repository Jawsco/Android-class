package com.example.simpleadapter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_main.*

class mainActivity : AppCompatActivity()
{

    var data1 = arrayOf("Korea", "Germany", "France", "Czech", "Roma")
    var data2 = arrayOf("Our country", "Middle Europe", "West Europe", "East Europe", "Empire")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.country)

        var list = ArrayList<HashMap<String, String>>()
        var idx = 0
        while(idx < data1.size)
        {
            var map = HashMap<String, String>()
            map.put("str1", data1[idx])
            map.put("str2", data2[idx])
            list.add(map)
            idx++
        }

        var key = arrayOf("str1", "str2")
        var ids = intArrayOf(android.R.id.text1, android.R.id.text2)
        var adapter = SimpleAdapter(this, list, android.R.layout.simple_expandable_list_item_2, key, ids)
        listView.adapter = adapter

        listView.setOnItemClickListener{adapter, view, i, l ->
            textView.text = data1[i]
        }
    }
}

/*
class MainActivity : AppCompatActivity() {

    var data1 = arrayOf("apple", "banana", "a1", "a2")
    var data2 = arrayOf("String1", "String2", "String3", "String4")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list = ArrayList<HashMap<String, String>>()
        var idx = 0
        while(idx < data1.size)
        {
            var map = HashMap<String, String>()
            map.put("str1", data1[idx])
            map.put("str2", data2[idx])
            list.add(map)
            idx++
        }

        var key = arrayOf("str1", "str2")
        var ids = intArrayOf(android.R.id.text1, android.R.id.text2)
        var adapter = SimpleAdapter(this, list, android.R.layout.simple_expandable_list_item_2, key, ids)
        listView.adapter = adapter

        listView.setOnItemClickListener{ adapterView, view, i, l ->
            textView.text = data1[i]
        }
    }
}
*/