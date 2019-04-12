package com.example.mymp3

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

public class RecyclerAdapter(val context : Context, val music_List, ArrayList<RecyclerItem>) :
        RecyclerView.Adapter<RecyclerAdapter.Holder>
{
    override fun onBindViewHolder(p0: Holder, p1: Int)
    {

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): [
    override fun getItemCount(): Int
    {

    }
    private var music_List = arrayListOf<RecyclerItem>()

    inner class Holder(itemView : View?) : RecyclerView.ViewHolder(itemView)
    {
        val album = itemView?.findViewById<ImageView>(R.id.album)
        val title = itemView?.findViewById<TextView>(R.id.title)
        val artist = itemView?.findViewById<TextView>(R.id.artist)
    }
}