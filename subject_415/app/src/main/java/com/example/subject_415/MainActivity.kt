package com.example.subject_415

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.Exception
import java.net.URL

data class imageInfo(val title : String, val url : String)

class MainActivity : AppCompatActivity()
{
    var imageInfo = ArrayList<imageInfo>()
    var path : String? = null

    var title : String = ""
    var url : String = ""

    var index = 0

    var permission_list = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermission()

        path = Environment.getExternalStorageDirectory().absolutePath +
                "/android/data/" + packageName

        var file = File(path)
        if(file.exists() == false)
        {
            file.mkdir()
        }

        val imageInfoAdapter = ListAdapter(this, imageInfo)
        imageListView.adapter = imageInfoAdapter

        try
        {
            var input = FileInputStream(path + "/File.dst")
            var dis = DataInputStream(input)

            while(true)
            {
                title = dis.readUTF()
                if(title == null)
                {
                    break
                }
                url = dis.readUTF()
                imageInfo.add(imageInfo(title, url))
            }

            dis.close()
        }
        catch (e : Exception)
        {
            e.printStackTrace()
        }

        button.setOnClickListener { view ->

            title = editText.text.toString()
            url = editText2.text.toString()

            try
            {
                var output = FileOutputStream(path + "/File.dst",true)
                var dos = DataOutputStream(output)
                dos.writeUTF(title)
                dos.writeUTF(url)
                dos.flush()
                dos.close()
            }
            catch (e : Exception)
            {
                e.printStackTrace()
            }

            imageInfo.add(imageInfo(title, url))
            imageListView.adapter = imageInfoAdapter
        }

        imageListView.setOnItemClickListener { parent, view, position, id ->
            index = position
            ThreadClass().start()
        }
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


    class ListAdapter(val context : Context, var list : ArrayList<imageInfo>) : BaseAdapter()
    {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View?
        {
            val view: View? = LayoutInflater.from(context).inflate(R.layout.imagelist, null)

            val title = view?.findViewById<TextView>(R.id.title)
            val url = view?.findViewById<TextView>(R.id.url)

            val info = list[position]

            title?.text = info.title
            url?.text = info.title + "사진"

            return view
        }

        override fun getItem(position: Int): Any
        {
            return list[position]
        }

        override fun getItemId(position: Int): Long
        {
            return 0
        }

        override fun getCount(): Int
        {
            return list.size
        }

    }

    inner class ThreadClass : Thread()
    {
        override fun run()
        {
            val url = imageInfo[index]
            var bitmap= BitmapFactory.decodeStream(URL(imageInfo[index].url).content as InputStream)
            runOnUiThread{
                imageView.setImageBitmap(bitmap)
            }
        }
    }

    override fun onDestroy()
    {
        super.onDestroy()
    }
}
