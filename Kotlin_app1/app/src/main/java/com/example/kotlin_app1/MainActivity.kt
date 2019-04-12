package com.example.kotlin_app1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    val SECOND_ACTIVITY = 1
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->
            var intent = Intent("com.test.second")
            intent.putExtra("data1", 100)
            startActivityForResult(intent, SECOND_ACTIVITY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        if(requestCode == SECOND_ACTIVITY)
        {
            if(resultCode == RESULT_OK)
            {
                var value1 = data?.getIntExtra("value1", 0)
                textView.text = "value1 : ${value1}"
            }
        }
    }
}
