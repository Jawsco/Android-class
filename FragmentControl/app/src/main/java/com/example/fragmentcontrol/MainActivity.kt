package com.example.fragmentcontrol

import android.graphics.Color.BLUE
import android.graphics.Color.WHITE
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    var input_fragment = FragmentInput()
    var result_fragment = FragmentOutput()

    var value1: String? = null
    var value2: String? = null

    var button_Left_Color = BLUE
    var button_Right_Color = WHITE

    var button_Text_Left: String? = null
    var button_Text_Right: String? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment("input")
        ThreadClass().start()

    }

    fun setFragment(name: String)
    {
        var tran = supportFragmentManager.beginTransaction()

        when (name)
        {
            "input" ->
            {
                tran.replace(R.id.container, input_fragment)
            }
            "result" ->
            {
                tran.replace(R.id.container, result_fragment)
                tran.addToBackStack(null)
            }
        }
        tran.commit()
    }

    inner class ThreadClass : Thread()
    {
        override fun run()
        {
            while (true)
            {
                SystemClock.sleep(10)
                runOnUiThread {
                    button3.setBackgroundColor(button_Left_Color)
                    button3.setText(button_Text_Left)

                    button4.setBackgroundColor(button_Right_Color)
                    button4.setText(button_Text_Right)
                }
            }
        }
    }
}
