package com.example.fileio

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream
import java.net.URL

class MainActivity : AppCompatActivity()
{
    var arrayList = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->
            arrayList.add(editText.text.toString())
            Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
        }

        button2.setOnClickListener { view ->
            ThreadClass().start()
        }
    }

    inner class ThreadClass : Thread()
    {
        override fun run()
        {
            val url = editText.text.toString()
            var bitmap = BitmapFactory.decodeStream(URL(url).content as InputStream)
            runOnUiThread{

                imageView.setImageBitmap(bitmap)

            }
        }
    }

    override fun onDestroy()
    {
        super.onDestroy()
    }
}
