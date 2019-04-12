package com.example.bluetooth

import android.bluetooth.BluetoothDevice
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class BTDevicesRVAdapter(val context : Context, val btDevices : Set<BluetoothDevice>) : RecyclerView.Adapter<BTDevicesRVAdapter.Holder>()
{
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder
    {
        val view = LayoutInflater.from(context).inflate(R.layout.btdevices_list, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int
    {
        return btDevices.size
    }

    override fun onBindViewHolder(p0: Holder, p1: Int)
    {
        p0.bind(btDevices.elementAt(p1), context)
    }

    inner class Holder(itemView : View?) : RecyclerView.ViewHolder(itemView!!)
    {
        val name = itemView?.findViewById<TextView>(R.id.btDevice_Name)
        val address = itemView?.findViewById<TextView>(R.id.btDevice_Address)

        fun bind(btDevice : BluetoothDevice, context : Context)
        {
            name?.text = btDevice.name
            address?.text = btDevice.address
        }
    }
}