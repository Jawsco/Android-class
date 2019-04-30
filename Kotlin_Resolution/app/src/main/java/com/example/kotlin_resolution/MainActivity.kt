package com.example.kotlin_resolution

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import kotlinx.android.synthetic.main.second_activity.*

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        if(savedInstanceState != null) // 회전할 경우 널이아닌 값이 반환된다
        {
            textView.text = savedInstanceState?.getString("data1")
        }

        button.setOnClickListener { view ->
            textView.text = editText.text
        }

        button2.setOnClickListener { view ->
            var intent = Intent(this, Main2Activity::class.java)
            intent.putExtra("data2", editText.text.toString())
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?)
    {
        super.onSaveInstanceState(outState)
        outState?.putString("data1", textView.text.toString())
    }
}
