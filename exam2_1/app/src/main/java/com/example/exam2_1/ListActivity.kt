package com.example.exam2_1

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_list.*
import java.io.DataInputStream
import java.io.FileInputStream

class ListActivity : AppCompatActivity()
{
    var infoList = arrayListOf<info>()
    var path : String? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        path = Environment.getExternalStorageDirectory().absolutePath +
                "/android/data/" + packageName
        try
        {
            var input = FileInputStream(path + "/sdFile.dat")
            var dis = DataInputStream(input)
            while(true)
            {
                var name = dis.readUTF()
                var phoneNumber = dis.readUTF()
                if(name == null)
                {
                    break
                }
                infoList.add(info(name, phoneNumber))
            }
            dis.close()
        }
        catch (e : Exception)
        {
            e.printStackTrace()
        }

        val lvAdapter = LVAdapter(this, infoList)
        listview.adapter = lvAdapter

        listview.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:"+infoList[position].phoneNumber))
            startActivity(intent)
        }
    }
}

class LVAdapter(val context : Context, var list : ArrayList<info>) : BaseAdapter()
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View?
    {
        val view: View? = LayoutInflater.from(context).inflate(R.layout.info_layout, null)

        val name = view?.findViewById<TextView>(R.id.textView3)
        val phoneNumber = view?.findViewById<TextView>(R.id.textView4)

        val info = list[position]

        name?.text = info.name
        phoneNumber?.text = info.phoneNumber

        return view
    }

    override fun getItem(position: Int): Any
    {
        return list[position]
    }

    override fun getItemId(position: Int): Long
    {
        return position.toLong()
    }

    override fun getCount(): Int
    {
        return list.size
    }
}
