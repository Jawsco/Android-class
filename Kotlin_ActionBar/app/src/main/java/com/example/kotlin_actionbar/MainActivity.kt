package com.example.kotlin_actionbar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var first = FirstFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu) //사용자 레이아웃 기본 메뉴로 설정
        return true // return true => 메뉴 화면 표시
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId)
        {
            R.id.item1 ->
            {
                textView.text = "홈을 선택했습니다"
                var tran = supportFragmentManager.beginTransaction()
                tran.replace(R.id.container, first)
                tran.addToBackStack(null)
                tran.commit()
            }
            R.id.item2 ->
            {
                textView.text = "프로필을 선택했습니다"
            }
            R.id.item3 ->
            {
                textView.text = "더보기를 선택했습니다"
            }
            R.id.item4 ->
            {
                textView.text = "설정을 선택했습니다"
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
