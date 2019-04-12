package com.example.helpme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main5.*

class Main5Activity : AppCompatActivity()
{
    var arr = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        //345 789 10 11 12
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        button12.setOnClickListener { view ->
            finish()
        }

        button13.setOnClickListener { view ->
            arr = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9)
        }

        imageView3.setOnClickListener { view ->
            var resourceId = resources.getIdentifier("x", "drawable", packageName)
            imageView3.setImageResource(resourceId)
            arr.set(0, 0)
        }
        imageView4.setOnClickListener { view ->
            var resourceId = resources.getIdentifier("x", "drawable", packageName)
            imageView4.setImageResource(resourceId)
        }
        imageView5.setOnClickListener { view ->
            var resourceId = resources.getIdentifier("x", "drawable", packageName)
            imageView5.setImageResource(resourceId)
        }
        imageView7.setOnClickListener { view ->
            var resourceId = resources.getIdentifier("x", "drawable", packageName)
            imageView7.setImageResource(resourceId)
        }
        imageView8.setOnClickListener { view ->
            var resourceId = resources.getIdentifier("x", "drawable", packageName)
            imageView8.setImageResource(resourceId)
        }
        imageView9.setOnClickListener { view ->
            var resourceId = resources.getIdentifier("x", "drawable", packageName)
            imageView9.setImageResource(resourceId)
        }
        imageView10.setOnClickListener { view ->
            var resourceId = resources.getIdentifier("x", "drawable", packageName)
            imageView10.setImageResource(resourceId)
        }
        imageView11.setOnClickListener { view ->
            var resourceId = resources.getIdentifier("x", "drawable", packageName)
            imageView11.setImageResource(resourceId)
        }
        imageView12.setOnClickListener { view ->
            var resourceId = resources.getIdentifier("x", "drawable", packageName)
            imageView12.setImageResource(resourceId)
        }
    }
}
