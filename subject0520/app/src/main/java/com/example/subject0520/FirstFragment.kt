package com.example.subject0520


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*
import kotlinx.android.synthetic.main.fragment_second.*
import java.util.*
import kotlin.collections.ArrayList

class FirstFragment : Fragment() {

    var dataList = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_first, container, false)

        dataList.add("조석호")
        dataList.add("조현윤")
        dataList.add("조현호")

        val adapter = ListViewAdapter(activity!!.applicationContext, dataList)
        var listview = view.findViewById<ListView>(R.id.lv)
        listview.adapter = adapter

        listview.setOnItemClickListener { parent, view, position, id ->
            var fragment = SecondFragment()

            var bundle = Bundle(1)
            bundle.putString("name", dataList[position])

            fragment.arguments = bundle

            var tabhost = activity!!.findViewById<TabLayout>(R.id.tabs)
            tabhost.getTabAt(1)!!.select()
        }

        return view
    }


}
