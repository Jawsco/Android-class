package com.example.subject0513


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_fourth.*

class FourthFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_fourth, container, false)

        var button = view?.findViewById<Button>(R.id.button)

        button?.setOnClickListener { view ->
            Toast.makeText(view.context, "버튼을 클릭하셨습니다", Toast.LENGTH_SHORT).show()
        }
        return view
    }


}
