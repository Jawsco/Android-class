package com.example.dialog

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->
            var builder = AlertDialog.Builder(this)
            builder.setTitle("basic dialog title")
            builder.setMessage("this is body")
            builder.setIcon(R.mipmap.ic_launcher)

            var listener = object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int)
                {
                    when(which)
                    {
                        DialogInterface.BUTTON_POSITIVE ->
                            textView.text = "Positive"
                        DialogInterface.BUTTON_NEGATIVE ->
                            textView.text = "Negative"
                    }
                }
            }
            builder.setCancelable(false)
            builder.setPositiveButton("Positive", listener)
            builder.setNegativeButton("Negative", listener)

            builder.show()
        }

        button2.setOnClickListener { view ->
            var builder = AlertDialog.Builder(this)
            builder.setTitle("커스텀 다이얼로그")
            builder.setIcon(R.mipmap.ic_launcher)


            var v1 = layoutInflater.inflate(R.layout.dialog, null)
            builder.setView(v1)

            var listener = object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int)
                {
                    var alert = dialog as AlertDialog
                    var edit1: EditText? = alert.findViewById<EditText>(R.id.editText)
                    var edit2: EditText? = alert.findViewById<EditText>(R.id.editText)
                    textView.text = "editt1 : ${edit1?.text}\n"
                    textView.append("edit2 : ${edit2?.text}")
                }
            }
            builder.setCancelable(false)
            builder.setPositiveButton("Positive", listener)
            builder.setNegativeButton("Negative", null)

            builder.show()
        }

        button3.setOnClickListener { view ->
            var calender = Calendar.getInstance()
            var year = calender.get(Calendar.YEAR)
            var month = calender.get(Calendar.MONTH)
            var day = calender.get(Calendar.DAY_OF_MONTH)

            var listener = object: DatePickerDialog.OnDateSetListener
            {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int)
                {
                    textView.text = "${year}년 ${month + 1}월 ${dayOfMonth}일"
                }
            }
            var picker = DatePickerDialog(this, listener, year, month, day)
            picker.show()
        }
    }
}
