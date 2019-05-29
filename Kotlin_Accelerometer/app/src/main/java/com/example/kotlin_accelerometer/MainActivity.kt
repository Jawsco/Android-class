package com.example.kotlin_accelerometer

import android.content.Context
import android.hardware.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var manager : SensorManager? = null
    var listener : SensorListener? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        listener = SensorListener()
        button.setOnClickListener { view ->
            var sensor = manager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            var sensor_value = manager?.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
            if(sensor_value == false)
            {
                textView.text = "가속도 센서 지원x"
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
            if(event?.sensor?.type == Sensor.TYPE_ACCELEROMETER)
            {
                textView.text = "x축 : ${event?.values[0]}\n"
                textView.append("y축 : ${event?.values[1]}\n")
                textView.append("z축 : ${event?.values[2]}\n")

                if(event?.values[0] > 3)
                {
                    Toast.makeText(applicationContext, "왼쪽으로 기울였습니다", Toast.LENGTH_SHORT).show()
                }
                else if(event?.values[0] < -3)
                {
                    Toast.makeText(applicationContext, "오른쪽으로 기울였습니다", Toast.LENGTH_SHORT).show()
                }
                else if(event?.values[1] > 3)
                {
                    Toast.makeText(applicationContext, "위로 기울였습니다", Toast.LENGTH_SHORT).show()
                }
                else if(event?.values[1] < -3 )
                {
                    Toast.makeText(applicationContext, "아래로 기울였습니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
