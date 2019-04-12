package com.example.permissions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row.*

data class Contact(var name : String, var phone_Number : String)


class MainActivity : AppCompatActivity()
{
    var contact_List = ArrayList<Contact>()

    var permission_list = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.WRITE_CONTACTS,
        Manifest.permission.SEND_SMS,
        Manifest.permission.RECEIVE_SMS
    )


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission()

        val phones = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
            null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )
        while (phones!!.moveToNext())
        {

            val name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

            contact_List.add(Contact(name, phoneNumber))
        }
        phones.close()

        val nameAdapter = ContactListAdapter(this, contact_List)
        contacts_Listview.adapter = nameAdapter
    }

    fun checkPermission()
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            return
        }
        for (permission: String in permission_list)
        {
            var chk = checkCallingOrSelfPermission(permission)
            if (chk == PackageManager.PERMISSION_DENIED)
            {
                requestPermissions(permission_list, 0)
                break
            }
        }
    }

    inner class ContactListAdapter(val context: Context, val contact_List: ArrayList<Contact>) : BaseAdapter()
    {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View?
        {
            val view: View? = LayoutInflater.from(context).inflate(R.layout.row, null)

            val name = view?.findViewById<TextView>(R.id.name_Textview)
            val number = view?.findViewById<TextView>(R.id.phone_Number_Textview)

            val contact = contact_List[position]
            name?.text = contact.name
            number?.text = contact.phone_Number

            return view
        }

        override fun getItem(position: Int): Any
        {
            return contact_List[position]
        }

        override fun getItemId(position: Int): Long
        {
            return 0
        }

        override fun getCount(): Int
        {
            return contact_List.size
        }
    }
}