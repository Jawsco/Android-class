package com.example.kotlin_tabbar

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var fragment_list = ArrayList<Fragment>()
    var title_list = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        //선택되지 않은 색상, 선택된 색상
        tabs.setTabTextColors(Color.WHITE, Color.RED)

        var sub1 = FirstFragment()
        fragment_list.add(sub1)
        title_list.add("tab1")
        var sub2 = SecondFragment()
        fragment_list.add(sub2)
        title_list.add("tab2")

        pager.adapter = PagerAdapter(supportFragmentManager)
        tabs.setupWithViewPager(pager)
    }

    inner class PagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm){
        override fun getItem(p0: Int): Fragment {
            return fragment_list.get(p0)
        }

        override fun getCount(): Int {
            return fragment_list.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return title_list.get(position)
        }

    }
}
