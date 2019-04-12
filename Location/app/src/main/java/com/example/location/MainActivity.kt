package com.example.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity()
{

    var locationManager : LocationManager? = null
    var mContext : Context? = null
    var permission_list = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission()

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?

        try
        {
            locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, locationListener)
        }
        catch (ex : SecurityException)
        {

        }
        mContext = this
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

    var locationListener : LocationListener = object : LocationListener
    {
        override fun onLocationChanged(location: Location)
        {
            textView.text = "longitude : " + location.latitude + "\nlongitude : " + location.longitude

            var format_latidue = Math.floor(location.latitude*1000)/1000.toDouble()
            var format_longitude = Math.floor(location.longitude*1000)/1000.toDouble()

            textView2.text = "longitude : " + format_latidue + "\nlongitude : " + format_longitude

            if(35.663 == format_latidue && 128.413 == format_longitude)
            {
                var toast = Toast.makeText(mContext, "대구소프트웨어고", Toast.LENGTH_LONG)
                toast.show()
            }
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

        override fun onProviderEnabled(provider: String?) {}

        override fun onProviderDisabled(provider: String?) {}

    }
}
