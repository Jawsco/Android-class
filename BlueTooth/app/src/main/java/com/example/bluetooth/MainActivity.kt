package com.example.bluetooth

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast

class MainActivity : AppCompatActivity()
{
    val permission_list = arrayOf(
        Manifest.permission.BLUETOOTH,
        Manifest.permission.BLUETOOTH_ADMIN
    )
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(BluetoothAdapter.getDefaultAdapter() == null) //블루투스 미지원
        {
            Toast.makeText(this, "블루투스 지원하지 않음", Toast.LENGTH_LONG).show()
            SystemClock.sleep(3000)
            finish()
        }
        else //지원
        {
            if(BluetoothAdapter.getDefaultAdapter().isEnabled)
            {

            }
            else
            {

            }
        }

        checkPermission()
    }

    fun checkPermission()
    {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            return
        }
        for(permission : String in permission_list)
        {
            var check = checkCallingOrSelfPermission(permission)
            if(check == PackageManager.PERMISSION_DENIED)
            {
                requestPermissions(permission_list, 0)
                break
            }
        }
    }
}
