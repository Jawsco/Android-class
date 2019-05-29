package com.example.kotlin_gobackactionbar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var action = supportActionBar
        action?.setHomeButtonEnabled(true)
        action?.setDisplayHomeAsUpEnabled(true) //뒤로가기 바 추가
        action?.setHomeAsUpIndicator(android.R.drawable.ic_menu_directions) // 아이콘 변경
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId)
        {
            android.R.id.home ->
            {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
