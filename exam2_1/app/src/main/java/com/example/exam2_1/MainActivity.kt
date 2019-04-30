package com.example.exam2_1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import kotlinx.android.synthetic.main.activity_main.*
import java.io.DataOutputStream
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity()
{
    var path : String? = null

    var permission_list = arrayOf(
        Manifest.permission.CALL_PHONE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
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

        button.setOnClickListener { view ->
            try
            {
                var output = FileOutputStream(path + "/sdFile.dat", true)
                var dos = DataOutputStream(output)
                dos.writeUTF(editText.text.toString())
                dos.writeUTF(editText2.text.toString())
                dos.flush()
                dos.close()
            }
            catch (e : Exception)
            {
                e.printStackTrace()
            }

            var intent = Intent(this, ListActivity::class.java)
            intent.putExtra("name", editText.text.toString())
            intent.putExtra("phoneNumber", editText2.text.toString())
            startActivity(intent)
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
}
