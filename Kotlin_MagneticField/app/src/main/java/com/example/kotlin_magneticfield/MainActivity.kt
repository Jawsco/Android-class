package com.example.kotlin_magneticfield

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var manager : SensorManager? = null
    var listener : SensorListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        listener = SensorListener()

        var sensor = manager?.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        var sensor_value = manager?.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)

        if(sensor_value == false)
        {
            textView.text = "마그네틱 센서 지원하지 않음"
        }
    }

    inner class SensorListener : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent?) {
            if(event?.sensor?.type == Sensor.TYPE_MAGNETIC_FIELD)
            {
                textView.text = "x축 : ${event?.values[0]}"
                textView.append("y축 : ${event?.values[1]}")
                textView.append("z축 : ${event?.values[2]}")
            }
        }
    }
}
