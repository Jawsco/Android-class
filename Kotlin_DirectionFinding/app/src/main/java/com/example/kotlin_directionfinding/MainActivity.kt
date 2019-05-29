package com.example.kotlin_directionfinding

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var manager: SensorManager? = null
    var listenr: SensorListener? = null
    var acc_value: FloatArray? = null
    var mag_value: FloatArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        listenr = SensorListener()

        button.setOnClickListener { view ->
            var sensor = manager?.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
            var sensor2 = manager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

            manager?.registerListener(listenr, sensor, SensorManager.SENSOR_DELAY_UI)
            manager?.registerListener(listenr, sensor2, SensorManager.SENSOR_DELAY_UI)
        }

        button2.setOnClickListener { view ->
            manager?.unregisterListener(listenr)
        }
    }

    inner class SensorListener : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent?) {
            when (event?.sensor?.type) {

                Sensor.TYPE_ACCELEROMETER -> {

                    acc_value = event?.values?.clone()

                } //복제

                Sensor.TYPE_MAGNETIC_FIELD -> {

                    mag_value = event?.values?.clone()

                }

            }

            if (mag_value != null && acc_value != null) {

                //행렬연산을 위해 필요한 부분

                var R = FloatArray(9)

                var I = FloatArray(9)

                SensorManager.getRotationMatrix(R, I, acc_value, mag_value)

                var values = FloatArray(3)

                SensorManager.getOrientation(R, values)

                var azimuth = radian2Degree(values[0])

                var pitch = radian2Degree(values[1])

                var roll = radian2Degree(values[2])

                textView.text = "방위값${azimuth}\n"

                textView.append("좌우기울기${pitch}\n")

                textView.append("앞뒤기울기${roll}\n")

                if(20 > azimuth && azimuth > -20){
                    Toast.makeText(applicationContext, "북쪽입니다", Toast.LENGTH_SHORT).show()
                }
                else if(110 > azimuth && azimuth > 70) {
                    Toast.makeText(applicationContext, "동쪽입니다", Toast.LENGTH_SHORT).show()
                }
                else if(180 > azimuth && azimuth > 160 || -180 < azimuth && -160 < azimuth) {
                    Toast.makeText(applicationContext, "남쪽입니다", Toast.LENGTH_SHORT).show()
                }
                else if(-110 < azimuth && -70 < azimuth) {
                    Toast.makeText(applicationContext, "서쪽입니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
        fun radian2Degree(radian : Float) : Float {
            return radian*180/Math.PI.toFloat()
        }
    }
}
