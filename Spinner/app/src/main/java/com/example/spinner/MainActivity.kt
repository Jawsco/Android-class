package com.example.spinner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    var data1 = arrayOf("spinner1-1", "spinner1-2", "spinner1-3", "spinner1-4", "spinner1-5")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, data1)

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter1

        var listener = SpinnerListener()
        spinner.onItemSelectedListener = listener
    }

    inner class SpinnerListener:AdapterView.OnItemSelectedListener
    {
        override fun onNothingSelected(parent: AdapterView<*>?)
        {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
        {
            textView.text = data1[position]
        }

    }
}
