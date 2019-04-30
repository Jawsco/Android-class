package com.example.kotlin_memo


import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    var input_fragment = MemoFragment()
    var result_fragment = MemoListFragment()
    var value1:String? = null //MemoFragment의 EditText내용을 가져옴
    var value2:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment("input")
    }
    fun setFragment(name : String){
        var tran = supportFragmentManager.beginTransaction()
        when(name){
            "input" -> {
                tran.replace(R.id.container, input_fragment)
            }
            "result" -> {
                tran.replace(R.id.container, result_fragment)
                tran.addToBackStack(null)
            }
        }
        tran.commit()
    }
}

