package com.example.koltlin_lightsensor

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
    var listener : MainActivity.SensorListener? = null

    var max_lux : Float = 0.0F
    var min_lux : Float = 10000.0F

    var prev_time : Long = 0L
    var cur_time : Long = 0L
    var prev_lux : Float = 150.0F
    var cur_lux : Float = 0.0F
    var threshold_lux : Float = 50.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        listener = SensorListener()

        button.setOnClickListener { view ->
            var sensor = manager?.getDefaultSensor(Sensor.TYPE_LIGHT)
            var sensor_value = manager?.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)

            prev_time = System.currentTimeMillis()

            if(sensor_value == false)
            {
                textView.text = "조도 센서를 지원하지 않습니다"
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
            if(event?.sensor?.type == Sensor.TYPE_LIGHT)
            {
                textView.text = "주변 밝기 : ${event?.values[0]} lux"
                if(max_lux < event?.values[0])
                {
                    max_lux = event?.values[0]
                    textView2.text = "최대 광량 : ${max_lux} lux"
                }
                if(min_lux > event?.values[0])
                {
                    min_lux = event?.values[0]
                    textView3.text = "최소 광량 : ${min_lux} lux"
                    cur_time = System.currentTimeMillis()
                    var t = cur_time - prev_time
                    prev_time = cur_time
                    if(t < 60000)
                    {
                        textView4.text = "앞에 물체가 있습니다 : ${t}ms"
                    }
                    else
                    {
                        textView4.text = "밤이 되었습니다"
                    }
                }

                cur_lux = event?.values[0]

                if((prev_lux - cur_lux) > threshold_lux)
                {
                    prev_lux = cur_lux
                    textView5.text = "앞에 물체가 있습니다"
                }
                else
                {
                    prev_lux = cur_lux
                    textView5.text = "점점 어두워지고 있습니다"
                }
            }
        }

    }
}
