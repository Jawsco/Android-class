package com.example.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class MainRvAdapter(val context : Context, val countryList : ArrayList<Country>) : RecyclerView.Adapter<MainRvAdapter.Holder>()
{
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder
    {
        val view = LayoutInflater.from(context).inflate(R.layout.country_rv_item, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int
    {
        return countryList.size
    }

    override fun onBindViewHolder(p0: Holder, p1: Int)
    {
        p0?.bind(countryList[p1], context)
    }

    inner class Holder(itemView : View?) : RecyclerView.ViewHolder(itemView!!)
    {
        val flag = itemView?.findViewById<ImageView>(R.id.flag)
        val name = itemView?.findViewById<TextView>(R.id.name)
        val continent = itemView?.findViewById<TextView>(R.id.continent)

        fun bind(country: Country, context: Context)
        {
            if (country.flag != "")
            {
                val resourceId = context.resources.getIdentifier(country.flag, "drawable", context.packageName)
                flag?.setImageResource(resourceId)
            }
            else
            {
                flag?.setImageResource(R.mipmap.ic_launcher)
            }
            name?.text = country.name
            continent?.text = country.continent
        }
    }
}