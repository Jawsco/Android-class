package com.example.formybrother

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memo_List.adapter = Memo
        memo_List.layoutManager = LinearLayoutManager(this)

    }

    data class memoData(val title: String)
    {

    }
}
