package com.example.projectwork

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {

    var sensorManger : SensorManager? = null
    var lightSensor : Sensor? = null //조도 센서
    var gyroscopeSensor : Sensor? = null //자이로스코프 센서
    var accelerometerSensor : Sensor? = null //가속도 센서
    var magneticSensor : Sensor? = null //자기장 센서
    var pressureSensor : Sensor? = null //고도/기압 센서
    var R1 = FloatArray(9)
    var orientaion = FloatArray(3) // 방향 센서

    var myLight : Float = 0.0f
    var myGroscope : FloatArray = FloatArray(3)
    var myAccelerometer : FloatArray = FloatArray(3)
    var myMagnetic : FloatArray = FloatArray(3)
    var myPressure : Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManger = getSystemService(SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManger?.getDefaultSensor(Sensor.TYPE_LIGHT)
        gyroscopeSensor = sensorManger?.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        accelerometerSensor = sensorManger?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        magneticSensor = sensorManger?.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        pressureSensor = sensorManger?.getDefaultSensor(Sensor.TYPE_PRESSURE)
    }

    override fun onResume() {
        super.onResume()
        sensorManger?.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_UI)
        sensorManger?.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_UI)
        sensorManger?.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_UI)
        sensorManger?.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_UI)
        sensorManger?.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        when(event?.sensor)
        {
            lightSensor ->
            {
                myLight = event?.values!![0]
                textView.text = myLight.toString() + "\n"
                Log.i("Light", "Light : " + String.format("%.4f", myLight))
            }
            gyroscopeSensor ->
            {
                myGroscope[0] = event?.values!![0]
                myGroscope[1] = event?.values!![1]
                myGroscope[2] = event?.values!![2]
                textView2.text = ""
                textView2.append(String.format("%.4f", myGroscope[0]) + "\n")
                textView2.append(String.format("%.4f", myGroscope[1]) + "\n")
                textView2.append(String.format("%.4f", myGroscope[2]) + "\n")
                Log.i("Gyroscope", "Gyroscope[X] : " + String.format("%.4f", myGroscope[0]) +
                " [Y] : " + String.format("%.4f", myGroscope[1]) + " [Z] : " + String.format("%.4f", myGroscope[2]))
            }
            accelerometerSensor ->
            {
                myAccelerometer[0] = event?.values!![0]
                myAccelerometer[1] = event?.values!![1]
                myAccelerometer[2] = event?.values!![2]
                textView3.text = ""
                textView3.append(String.format("%.4f", myAccelerometer[0]) + "\n")
                textView3.append(String.format("%.4f", myAccelerometer[1]) + "\n")
                textView3.append(String.format("%.4f", myAccelerometer[2]) + "\n")
                Log.i("Accelerometer", "Accelerometer[X] : " + String.format("%.4f", myAccelerometer[0]) +
                        " [Y] : " + String.format("%.4f", myAccelerometer[1]) + " [Z] : " + String.format("%.4f", myAccelerometer[2]))
            }
            magneticSensor ->
            {
                myMagnetic[0] = event?.values!![0]
                myMagnetic[1] = event?.values!![1]
                myMagnetic[2] = event?.values!![2]
                textView4.text = ""
                textView4.append(String.format("%.4f", myMagnetic[0]) + "\n")
                textView4.append(String.format("%.4f", myMagnetic[1]) + "\n")
                textView4.append(String.format("%.4f", myMagnetic[2]) + "\n")
                Log.i("Magnetic", "Magnetic[X] : " + String.format("%.4f",  myMagnetic[0]) +
                        " [Y] : " + String.format("%.4f",  myMagnetic[1]) + " [Z] : " + String.format("%.4f",  myMagnetic[2]))
            }
            pressureSensor ->
            {
                myPressure = event?.values!![0]
                textView6.text = String.format("%.4f", myPressure) + "\n"
                Log.i("Pressure", "Pressure : " + String.format("%.4f", myPressure))
            }
        }
        SensorManager.getRotationMatrix(R1, null, myAccelerometer, myMagnetic)
        SensorManager.getOrientation(R1, orientaion)
        textView5.text = ""
        textView5.append(String.format("%.4f", orientaion?.get(0)) + "\n")
        textView5.append(String.format("%.4f", orientaion?.get(1)) + "\n")
        textView5.append(String.format("%.4f", orientaion?.get(2)) + "\n")
        Log.i("Orientaion", "Orientaion[X] : " + String.format("%.4f", orientaion?.get(0)) +
                " [Y] : " + String.format("%.4f", orientaion?.get(1)) + " [Z] : " + String.format("%.4f", orientaion?.get(2)))
    }
}
