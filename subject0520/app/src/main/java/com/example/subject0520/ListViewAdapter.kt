package com.example.subject0520

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter(val context : Context, val list : ArrayList<String>) : BaseAdapter() {

    private var lvItemList = list

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view : View = LayoutInflater.from(context).inflate(R.layout.customlistview, null)

        val text = view.findViewById<TextView>(R.id.textView)

        text.text = lvItemList.get(position)

        return view
    }

    override fun getItem(position: Int): Any {
        return lvItemList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return lvItemList.size
    }

}