package com.example.subject0520

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var fragmentList = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        tabs.setTabTextColors(Color.WHITE, Color.RED)

        var sub = FirstFragment()
        fragmentList.add(sub)

        var sub2 = SecondFragment()
        fragmentList.add(sub2)

        pager.adapter = PagerAdapter(supportFragmentManager)
        tabs.setupWithViewPager(pager)
    }

    inner class PagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(p0: Int): Fragment {
            return fragmentList.get(p0)
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return "tab"
        }

    }
}
