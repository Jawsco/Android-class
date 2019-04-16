package com.example.kotlin_resource

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->
            var str2 = resources.getString(R.string.str2)
            textView.text = str2
        }

        button2.setOnClickListener { view ->
            textView.text = ""
            var string_array = resources.getStringArray(R.array.string_array)
            for(str : String in string_array)
            {
                textView.append("${str}\n")
            }
        }

        button3.setOnClickListener { view ->
            textView.setTextColor(Color.YELLOW)
            /* 버전별로 색상이 다르게 나옴
            var red = Color.rgb(255, 0, 0)
            var ared = Color.argb(50, 255, 0, 0)
            */

            //버전이 달라도 색상이 비슷함
            var color = ContextCompat.getColor(this, R.color.color_1)
            textView.setTextColor(color)
        }
    }
}
