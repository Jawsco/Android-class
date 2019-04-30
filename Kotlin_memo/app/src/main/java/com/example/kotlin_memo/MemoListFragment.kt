package com.example.kotlin_memo


import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v4.app.ListFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class MemoListFragment : ListFragment()
{
    var btn: Button? = null
    var listview: ListView? = null
    var list = ArrayList<HashMap<String, Any>>() //리스트 데이터
    var adapter: SimpleAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        var view = inflater!!.inflate(R.layout.fragment_list, container, false)
        btn = view.findViewById<Button>(R.id.button)

        var main_activity = activity as MainActivity
        var momo_txt = main_activity.value1
        var user_txt = main_activity.value2

        btn?.setOnClickListener { view ->
            main_activity.supportFragmentManager.popBackStack()
        }

        //insert
        //실습:
        //1.만약 메모프래그먼트에서 넘겨받은 문자열이 null일경우 수행하지않는다.
        //2.메모 삭제버튼 추가 (ListFragment)
        //3.메모 내용에 작성자 추가(MemoFragment)
        var helper = DBHelper(main_activity)
        var db = helper.writableDatabase
        var sql = "insert into Memo (textData, dateData, userData) values (?, ?, ?)"
        val instance = Calendar.getInstance()
        val year = instance.get(Calendar.YEAR).toString()
        val month = (instance.get(Calendar.MONTH) + 1).toString()
        val date = instance.get(Calendar.DATE).toString()
        if (momo_txt == "")
        {
            db.close()
        }
        else
        {
            var arg1 = arrayOf(momo_txt, year + month + date, user_txt)
            db.execSQL(sql, arg1)
            db.close()
        }


        //조회
        db = helper.writableDatabase
        sql = "select * from Memo"
        var c: Cursor = db.rawQuery(sql, null)
        while (c.moveToNext())
        {
            var textData_pos = c.getColumnIndex("textData")
            var dateData_pos = c.getColumnIndex("dateData")
            var userData_pos = c.getColumnIndex("userData")
            var textData = c.getString(textData_pos)
            var dateData = c.getString(dateData_pos)
            var userData = c.getString(userData_pos)
            var map = HashMap<String, Any>()
            map.put("data1", textData) //메모내용
            map.put("data2", dateData) //메모작성일
            map.put("data3", userData)
            list.add(map)
        }
        db.close()
        var keys = arrayOf("data1", "data2", "data3")
        var ids = intArrayOf(R.id.textView1, R.id.textView2, R.id.textView3)
        //var activities=activity as MainActivity  다른 액티비티에서 호출될수도 있는경우
        adapter = SimpleAdapter(activity, list, R.layout.row1, keys, ids)
        listview = view.findViewById<ListView>(android.R.id.list)
        listview?.adapter = adapter as ListAdapter?
        return view
    }

    //list 클릭리스너 오버라이드
    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long)
    {
        super.onListItemClick(l, v, position, id)

        var idxList = arrayListOf<Int>()

        var main_activity = activity as MainActivity
        var helper = DBHelper(main_activity)
        var db = helper.writableDatabase
        var sql = "select * from Memo"
        var c: Cursor = db.rawQuery(sql, null)
        while(c.moveToNext())
        {
            var idx_pos = c.getColumnIndex("idx")
            var idx = c.getInt(idx_pos)
            idxList.add(idx)
        }
        sql = "delete from Memo where idx = " + idxList[position]
        db.execSQL(sql)
        db.close()
    }
}

