package com.example.kotlin_appbarlayout

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //styles.xml 수정없이 kt파일에서 바로 ActionBar 없애기
        supportActionBar?.hide()
        toolbar.title = "appbarLayout"

        /*
        //잘못된 접근법
        //toolbar.setTitleTextColor(Color.WHITE)

        //접혀있을 때 타이틀 색상
        toolbar_layout.setCollapsedTitleTextColor(Color.CYAN)

        //펼쳐질때 타이틀 색상
        toolbar_layout.setExpandedTitleColor(Color.RED)

        toolbar_layout.collapsedTitleGravity = Gravity.CENTER_HORIZONTAL
        toolbar_layout.expandedTitleGravity = Gravity.CENTER + Gravity.TOP
        */

        app_bar_image.setImageResource(R.mipmap.ic_launcher)
        toolbar_layout.setCollapsedTitleTextColor(Color.BLACK)
        toolbar_layout.setExpandedTitleColor(Color.BLACK)

        button1.setOnClickListener { view ->

        }
    }
}
