package com.example.kotlin_pressuresensor

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

    var max_bar : Float = 0.0F
    var min_bar : Float = 2000.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        listener = SensorListener()

        button.setOnClickListener { view ->
            var sensor = manager?.getDefaultSensor(Sensor.TYPE_PRESSURE)
            var sensor_value = manager?.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)
            if(sensor_value == false)
            {
                textView.text = "기압 센서를 지원하지 않음"
            }
        }

        button2.setOnClickListener { view ->
            manager?.unregisterListener(listener)
        }
    }

    inner class SensorListener : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent?) {
            when(event?.sensor?.type)
            {
                Sensor.TYPE_PRESSURE ->
                {
                    if(max_bar < event?.values[0])
                    {
                        max_bar = event?.values[0]
                        textView3.text = "최대 기압 : ${max_bar}"
                    }
                    if(min_bar > event?.values[0])
                    {
                        min_bar = event?.values[0]
                        textView4.text = "최소 기압 : ${min_bar}"
                    }

                    textView2.text = "현재 기압 : ${event?.values[0]}"

                    var cur_height = (1013.0 - event?.values[0]) / 0.1
                    textView.text = "현재 높이 : ${cur_height}m"

                    if((event?.values[0] - min_bar ) > 0)
                    {
                        var stairs = (event?.values[0] - min_bar) / 0.26

                        var str = String.format("%.2f", stairs)
                        textView5.text = "내려간 층수 : ${str}층"
                    }
                    if((max_bar - event?.values[0]) > 0)
                    {
                        var stairs = (max_bar - event?.values[0]) / 0.26

                        var str = String.format("%.2f", stairs)
                        textView6.text = "올라간 층수 : ${str}층"
                    }
                }
            }
        }
    }
}
