package com.example.test_lisrfragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_list.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListFragment : ListFragment()
{

    var textView: TextView? = null
    var button : Button? = null

    var imageResource = intArrayOf(R.drawable.vodka, R.drawable.j, R.drawable.jambong,
            R.drawable.tang, R.drawable.gamja)

//    var data1 = resources.getStringArray(R.array.data1)//음식이름
//    var data2 = resources.getStringArray(R.array.data2)//재료
    var data3 = arrayOf(500, 5000, 6000, 10000, 1000)
    var list = ArrayList<HashMap<String, Any>>()
    var checkItem = setOf<String>()

    var count : Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        var view = inflater!!.inflate(R.layout.fragment_list, container, false)
        var act = activity as MainActivity

        textView = view.findViewById<TextView>(R.id.textView)
        button = view.findViewById<Button>(R.id.button)

        button?.setOnClickListener { view ->
            textView?.text = count.toString() + "원 입니다\n"
        }

        var idx = 0
        while(idx < act.data1.size)
        {
            var map = HashMap<String, Any>()
            map.put("flag", imageResource[idx])
            map.put("data1", act.data1[idx])
            map.put("data2", act.data2[idx])
            map.put("data3", data3[idx])
            list.add(map)
            idx++
        }
        var keys = arrayOf("flag", "data1", "data2", "data3")
        var ids = intArrayOf(R.id.imageView, R.id.textView2, R.id.textView3, R.id.textView4)
        var adapter = SimpleAdapter(activity, list, R.layout.row1, keys, ids)
        var listView = view.findViewById<ListView>(android.R.id.list)
        listView?.adapter = adapter
        return view
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long)
    {
        var temp = ""
        temp = list[position].get("data3").toString()

        if(textView?.text!!.contains(list[position].get("data1").toString().toRegex())) //아이템이 있을때
        {
            textView?.text = textView?.text?.replace((list[position].get("data1").toString()+"\n").toRegex(), "")
            count -= temp.toInt()
        }
        else //아이템이 없을때
        {
            textView?.append(list[position].get("data1").toString() + "\n")
            count += temp.toInt()
        }
    }
}
