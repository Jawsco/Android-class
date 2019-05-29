package com.example.subject0527

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    var manager : SensorManager? = null
    var listener : SensorListener? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        listener = SensorListener()

        button.setOnClickListener { view ->
            var sensor = manager?.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            var sensor_value = manager?.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)

            if(sensor_value == false)
            {
                Toast.makeText(this, "근접 센서를 지원하지 않습니다", Toast.LENGTH_SHORT).show()
            }
        }

        button2.setOnClickListener { view ->
            manager?.unregisterListener(listener)
        }
    }

    inner class SensorListener : SensorEventListener
    {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int)
        {

        }

        override fun onSensorChanged(event: SensorEvent?)
        {
            when(event?.sensor?.type)
            {
                Sensor.TYPE_PROXIMITY ->
                {
                    textView.text = event?.values[0].toString()
                    if(event?.values[0] == 0.0F)
                    {
                        button.setBackgroundColor(Color.GREEN)
                    }
                    else
                    {
                        button.setBackgroundColor(Color.RED)
                    }
                }
            }
        }

    }
}
