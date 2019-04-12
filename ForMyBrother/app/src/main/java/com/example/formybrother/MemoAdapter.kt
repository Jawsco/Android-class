package com.example.formybrother

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MemoAdapter(val context : Context, val memoList, ArrayList<Memo>) : RecyclerView.Adapter<MemoAdapter.Holder>()
{
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder
    {

    }

    override fun getItemCount(): Int
    {
        return Memo.size
    }

    override fun onBindViewHolder(p0: Holder, p1: Int)
    {

    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
    {
        val content = itemView?.findViewById<TextView>(R.id.content)
    }
}