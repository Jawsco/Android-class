package com.example.kotlin_searchview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.support.v7.widget.SearchView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var searchView:SearchView? = null
    var data_list = arrayOf("aaaa", "bbbb", "cccc", "aabb", "ccdd", "aacc", "aadd", "bbcc", "bbdd")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_list)
        list1.adapter = adapter
        list1.isTextFilterEnabled = true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        var item = menu?.findItem(R.id.item1)
        var listener = ActionListener()
        item?.setOnActionExpandListener(listener)

        searchView = item?.actionView as SearchView
        searchView?.queryHint = "입력해주세요"
        var listener2 = ActionListener2()
        searchView?.setOnQueryTextListener(listener2)

        return true
    }

    inner class ActionListener : MenuItem.OnActionExpandListener {
        override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
            textView.text = "검색을 하려면 위에 검색 버튼을 누르세요"
            return true
        }

        override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
            textView.text = "검색하고자하는 문자열을 입력하세요"
            return true
        }
    }

    inner class ActionListener2 : SearchView.OnQueryTextListener{
        //키보드의 확인 버튼을 눌렀을 때
        override fun onQueryTextSubmit(query: String?): Boolean {
            textView2.text = "엔터키 : ${query}"
            searchView?.clearFocus()
            return true
        }

        //입력할 때 호출
        override fun onQueryTextChange(newText: String?): Boolean {
            textView2.text = newText
            list1.setFilterText(newText)
            if(newText?.length == 0)
            {
                list1.clearTextFilter()
            }
            return true
        }

    }
}
