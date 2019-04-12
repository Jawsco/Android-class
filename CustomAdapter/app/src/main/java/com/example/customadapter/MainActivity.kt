package com.example.customadapter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row.*

class MainActivity : AppCompatActivity()
{

    inner class ListAdpater: BaseAdapter()
    {
        var listener = BtnListener()
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View
        {
            var convertView:View? = p1
            if(p1 == null)
            {
                 convertView=layoutInflater.inflate(R.layout.row.null)
            }
            var text:TextView? = convertView?.findViewById<TextView>(R.id.textView)
        }

        override fun getItem(position: Int): Any
        {
        }

        override fun getItemId(position: Int): Long
        {
            return 0;
        }

        override fun getCount(): Int
        {

        }

    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var adapter = ListAdpater()
        listView.adapter = adapter
    }

    inner class BtnListener:View.OnClickListener
    {
        override fun onClick(v: View?)
        {
            var position = v?.tag as Int
            when(v?.id)
            {
                R.id.button ->
                    textView.text = "${position} : Clicked First Button"
                R.id.button2 ->
                    textView2.text = "${position} : Clicked Second Button"
            }
        }

    }
}


