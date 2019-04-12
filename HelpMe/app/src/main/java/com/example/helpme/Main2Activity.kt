package com.example.helpme

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.dialog.*
import org.w3c.dom.NameList
import org.w3c.dom.Text

data class nameData(var k_name : String, var e_name : String)

class Main2Activity : AppCompatActivity()
{
    var namearr = arrayListOf<nameData>()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        button5.setOnClickListener { view ->
            namearr.add(nameData(editText.text.toString(), editText2.text.toString()))

            val nameListAdapter = NameListAdapter(this, namearr)
            nameListView.adapter = nameListAdapter
        }

        button6.setOnClickListener { view ->
            finish()
        }

        nameListView.setOnItemClickListener { parent, view, position, id ->
            var builder = AlertDialog.Builder(this)
            builder.setTitle("basic dialog title")
            builder.setMessage("삭제하시겠습니까")
            builder.setIcon(R.mipmap.ic_launcher)

            var listener = object: DialogInterface.OnClickListener
            {
                override fun onClick(dialog: DialogInterface?, which: Int)
                {
                    when(which)
                    {
                        DialogInterface.BUTTON_POSITIVE ->
                        {
                            namearr.removeAt(position)
                            val nameListAdapter = NameListAdapter(this@Main2Activity, namearr)
                            nameListView.adapter = nameListAdapter
                        }
                        DialogInterface.BUTTON_NEUTRAL ->
                        {
                            var builder = AlertDialog.Builder(this@Main2Activity)
                            builder.setTitle("커스텀 다이얼로그")
                            builder.setIcon(R.mipmap.ic_launcher)

                            var v= layoutInflater.inflate(R.layout.dialog, null)
                            builder.setView(v)

                            var listener = object : DialogInterface.OnClickListener
                            {
                                override fun onClick(dialog: DialogInterface?, which: Int)
                                {
                                    var alert = dialog as AlertDialog
                                    var edit1 : EditText? = alert.findViewById<EditText>(R.id.editText3)
                                    var edit2 : EditText? = alert.findViewById<EditText>(R.id.editText4)

                                    namearr.set(position, nameData(edit1?.text.toString(), edit2?.text.toString()))
                                    val nameListAdapter = NameListAdapter(this@Main2Activity, namearr)
                                    nameListView.adapter = nameListAdapter
                                }
                            }
                            builder.setPositiveButton("수정", listener)
                            builder.setNegativeButton("취소", null)
                            builder.show()
                        }
                    }
                }
            }

            builder.setPositiveButton("삭제", listener)
            builder.setNegativeButton("취소", null)
            builder.setNeutralButton("내용 수정", listener)

            builder.show()
        }
    }
}

class NameListAdapter(val context: Context, var list: ArrayList<nameData>) : BaseAdapter()
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View?
    {
        val view: View? = LayoutInflater.from(context).inflate(R.layout.name_list, null)

        val kname = view?.findViewById<TextView>(R.id.textView2)
        val ename = view?.findViewById<TextView>(R.id.textView3)

        val info = list[position]

        kname?.text = info.k_name
        ename?.text = info.e_name

        return view
    }

    override fun getItem(position: Int): Any
    {
        return list[position]
    }

    override fun getItemId(position: Int): Long
    {
        return 0
    }

    override fun getCount(): Int
    {
        return list.size
    }
}
