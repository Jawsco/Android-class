package com.example.subjet

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

data class Name(var Korean_Name : String, var English_Name : String)

class MainActivity : AppCompatActivity()
{
    var name_List = ArrayList<Name>()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        insert_Button.setOnClickListener { view ->
            /*
            var tmpName : Name
            tmpName.Korean_Name = "test"

            tmpName.Korean_Name = "test"
            tmpName.English_Name = "engtest"
            name_List.add(tmpName)
            */
            name_List.add(Name(Korean_Name_EditText.text.toString(), English_Name_EditText.text.toString()))
            val nameAdapter = NameListAdapter(this, name_List)
            name_ListView.adapter = nameAdapter
        }

        name_ListView.setOnItemClickListener { parent, view, position, id ->
            name_List.removeAt(position)
            val nameAdapter = NameListAdapter(this, name_List)
            name_ListView.adapter = nameAdapter
        }

    }

    inner class NameListAdapter(val context: Context, val nameList: ArrayList<Name>) : BaseAdapter()
    {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View?
        {
            val view : View? = LayoutInflater.from(context).inflate(R.layout.list_layout, null)

            val korean_Name = view?.findViewById<TextView>(R.id.Korean_Name_TextView)
            val english_Name = view?.findViewById<TextView>(R.id.English_Name_TextView)

            val Name = nameList[position]
            korean_Name?.text = Name.Korean_Name
            english_Name?.text = Name.English_Name

            return view
        }

        override fun getItem(position: Int): Any
        {
            return nameList[position]
        }

        override fun getItemId(position: Int): Long
        {
            return 0
        }

        override fun getCount(): Int
        {
            return nameList.size
        }
    }
}
