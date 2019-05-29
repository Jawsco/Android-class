package com.example.subject0513

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    var first = FirstFragment()
    var second = SecondFragment()
    var third = ThirdFragment()
    var fourth = FourthFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId)
        {
            R.id.item1->
            {
                var tran = supportFragmentManager.beginTransaction()
                tran.replace(R.id.container, first)
                tran.addToBackStack(null)
                tran.commit()
            }
            R.id.item2->
            {
                var tran = supportFragmentManager.beginTransaction()
                tran.replace(R.id.container, second)
                tran.addToBackStack(null)
                tran.commit()
            }
            R.id.item3->
            {
                var tran = supportFragmentManager.beginTransaction()
                tran.replace(R.id.container, third)
                tran.addToBackStack(null)
                tran.commit()
            }
            R.id.item4->
            {
                var tran = supportFragmentManager.beginTransaction()
                tran.replace(R.id.container, fourth)
                tran.addToBackStack(null)
                tran.commit()
            }
        }
        return true
    }
}
