package com.example.subject0520


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.customlistview.*
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_second, container, false)

        var name = arguments?.getString("name")

        textView2?.text = name

        var button = view!!.findViewById<Button>(R.id.button)
        button.setOnClickListener { view ->
            textView3.text = editText.text
        }

        return view
    }


}
