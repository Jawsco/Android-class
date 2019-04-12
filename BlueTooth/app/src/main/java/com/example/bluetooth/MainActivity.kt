package com.example.bluetooth

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.btdevices_list.*

class MainActivity : AppCompatActivity()
{
    val permission_list = arrayOf(
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN
    )

    override fun onCreate(savedInstanceState: Bundle?)
    {
        var mBlueToothAdapter = BluetoothAdapter.getDefaultAdapter()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener { view ->
            checkPermission()
            if (BluetoothAdapter.getDefaultAdapter() == null) //블루투스 미지원
            {
                Toast.makeText(this, "블루투스 지원하지 않음", Toast.LENGTH_LONG).show()
                SystemClock.sleep(3000)
                finish()
            }
            else //지원
            {
                if (mBlueToothAdapter.isEnabled) //블루투스 on
                {
                    var pairedDevices: Set<BluetoothDevice> = mBlueToothAdapter.bondedDevices
                    if (pairedDevices.size > 0)
                    {
                        for (device in pairedDevices)
                        {
                            val Adapter = BTDevicesRVAdapter(this, pairedDevices)
                            btDevices_rv.adapter = Adapter

                            val lm = LinearLayoutManager(this)
                            btDevices_rv.layoutManager = lm
                            btDevices_rv.setHasFixedSize(true)
                        }
                    }
                }
                else //off
                {
                    intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    startActivityForResult(intent, 1)
                }
            }
        }
        button2.setOnClickListener { view ->
            mBlueToothAdapter.disable()
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
            var check = checkCallingOrSelfPermission(permission)
            if (check == PackageManager.PERMISSION_DENIED)
            {
                requestPermissions(permission_list, 0)
                break
            }
        }
    }
}
