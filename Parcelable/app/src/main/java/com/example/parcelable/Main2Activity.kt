package com.example.parcelable

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var t = intent.getParcelableExtra<TestClass>("test")
        textView.text = "나이 : ${t.age}"
        textView2.text = "이름 : ${t.name}"
        textView3.text = "주소 :  ${t.address}"
        textView4.text = "전화번호 : ${t.phone_number}"
    }
}
