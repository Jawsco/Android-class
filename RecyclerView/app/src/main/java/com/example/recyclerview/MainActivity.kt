package com.example.recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var countryList = arrayListOf<Country>(
            Country("usa", "미합중국", "북아메리카"),
            Country("kor", "대한민국", "동북아시아"),
            Country("aus", "오스트리아 공화국", "중부유럽"),
            Country("ger", "독일 연방", "중부유럽"),
            Country("swe", "스웨덴 왕국", "북유럽"),
            Country("rus", "러시아 연방", "동유럽"),
            Country("eng", "U.K.", "서유럽"),
            Country("spa", "스페인 왕국", "이베리아 반도"),
            Country("nor", "노르웨이 공화국", "븍유럽"),
            Country("byz", "비잔티움 제국", "남유럽")
        )

        val Adapter = MainRvAdapter(this, countryList)
        country_rv.adapter = Adapter

        val lm = LinearLayoutManager(this)
        country_rv.layoutManager = lm
        country_rv.setHasFixedSize(true)
    }
}
