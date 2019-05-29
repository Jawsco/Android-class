package com.example.subject0521

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.abs

class MainActivity : AppCompatActivity() {

    var manager : SensorManager? = null
    var listener : SensorListener? = null

    var prev_lux : Float = 0.0F
    var cur_lux : Float = 0.0F
    var threshold_lux = 30.0F

    var max_bar : Float = 0.0F
    var min_bar : Float = 2000.0F

    var prev_pre : Double = 0.0
    var pre_flag : Boolean = true
    var roof : Int = 0

    var kcal : Int = 8

    //1층에 7kcal
    //65kg x 1분 = 8kcal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        listener = SensorListener()

        button.setOnClickListener { view ->
            var sensor = manager?.getDefaultSensor(Sensor.TYPE_LIGHT)
            var sensor2 = manager?.getDefaultSensor(Sensor.TYPE_PRESSURE)
            var sensor_value = manager?.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)
            var sensor_value2 = manager?.registerListener(listener, sensor2, SensorManager.SENSOR_DELAY_UI)

            if(sensor_value == false)
            {
                textView.text = "조도 센서 없음"
            }
            if(sensor_value2 == false)
            {
                textView3.text = "기압 센서 없음"
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
                Sensor.TYPE_LIGHT ->
                {
                    textView.text = "조도 값 : ${event?.values[0]} lux"

                    cur_lux = event?.values[0]

                    if(cur_lux > (prev_lux + threshold_lux))
                    {
                        prev_lux = cur_lux
                        textView2.text = "밝아졌습니다"
                    }
                    else
                    {
                        prev_lux = cur_lux
                        textView2.text = "평상시입니다"
                    }
                }
                Sensor.TYPE_PRESSURE ->
                {
                    if(pre_flag)
                    {
                        prev_pre = event?.values[0].toDouble()
                        prev_pre = (1013.0 - prev_pre) / 0.1
                        pre_flag = false
                    }
                    textView3.text = "현재 기압 : ${event?.values[0]}"

                    if(max_bar < event?.values[0])
                    {
                        max_bar = event?.values[0]
                        textView4.text = "최대 기압 : ${max_bar}"
                    }
                    if(min_bar > event?.values[0])
                    {
                        min_bar = event?.values[0]
                        textView5.text = "최소 기압 : ${min_bar}"
                    }

                    var cur_height = (1013.0 - event?.values[0]) / 0.1
                    textView6.text = "현재 높이 : ${cur_height}m"

                    Log.d("height", cur_height.toString())
                    Log.d("height2", prev_pre.toString())
                    if(abs(cur_height - prev_pre) >= 2.6)
                    {
                        Log.d("roof", roof.toString())
                        roof++
                        pre_flag = true
                    }

                    if((event?.values[0] - min_bar ) > 0)
                    {
                        var stairs = (event?.values[0] - min_bar) / 0.26

                        var str = String.format("%.2f", stairs)
                        textView7.text = "내려간 층수 : ${str}층"
                    }
                    if((max_bar - event?.values[0]) > 0)
                    {
                        var stairs = (max_bar - event?.values[0]) / 0.26

                        var str = String.format("%.2f", stairs)
                        textView8.text = "올라간 층수 : ${str}층"
                    }
                    textView9.text = "소모 칼로리 ${roof*kcal}"

                }
            }
        }
    }
}
