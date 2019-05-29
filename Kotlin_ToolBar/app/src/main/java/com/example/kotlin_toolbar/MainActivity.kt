package com.example.kotlin_toolbar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "툴바입니다"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId)
        {
            R.id.item1 ->
            {
                textView.text = "홈을 눌렀습니다"
            }
            R.id.item2 ->
            {
                textView.text = "프로필을 눌렀습니다"
            }
            R.id.item3 ->
            {
                textView.text = "더보기를 눌렀습니다"
            }
            R.id.item4 ->
            {
                textView.text = "설정을 눌렀습니다"
            }
        }
        return true
    }
}
