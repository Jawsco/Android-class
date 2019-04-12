package com.example.toast

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->
            var toast = Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT)
            toast.show()
        }

        button2.setOnClickListener { view ->
            var v1 = layoutInflater.inflate(R.layout.simple_toast, null)
            v1?.setBackgroundResource(android.R.drawable.toast_frame)

            var image_view: ImageView? = v1.findViewById<ImageView>(R.id.image)
            image_view?.setImageResource(android.R.drawable.btn_star)


            var text_view: TextView? = v1.findViewById<TextView>(R.id.text)
            text_view?.text = "Hi there, It is me"
            text_view?.setTextColor(Color.WHITE)

            var t2 = Toast(this)
            t2.view = v1
            t2.setGravity(Gravity.CENTER, 0, 400)
            t2.show()
        }
    }
}
