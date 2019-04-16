package com.example.test_lisrfragment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity()
{
    var list_fragment = ListFragment()
    var data1 = emptyArray<String>()
    var data2 = emptyArray<String>()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data1 = resources.getStringArray(R.array.data1)//음식이름
        data2 = resources.getStringArray(R.array.data2)//재료

        var tran = supportFragmentManager.beginTransaction()
        tran.replace(R.id.container, list_fragment)
        tran.commit() //프래그먼트가 액티비티에 올라옴
    }
}
