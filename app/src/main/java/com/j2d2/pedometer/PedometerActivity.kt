package com.j2d2.pedometer

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_pedometer.*

class PedometerActivity : AppCompatActivity() {

//    private val sensorManager: SensorManager by lazy {
//        getSystemService(Context.SENSOR_SERVICE) as SensorManager
//    }

    private lateinit var sensorManager: SensorManager

    private val eventListener: SensorEventListener = object: SensorEventListener {
        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

        }

        override fun onSensorChanged(p0: SensorEvent?) {
            p0?.let {
                if(p0.sensor.type == Sensor.TYPE_STEP_COUNTER) {
                    txtStepCount.setText("Step Count : ${p0.values[0]}")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedometer)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            // Success! There's a pressure sensor.
        } else {
            // Failure! No pressure sensor.
        }
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        sensorManager.registerListener(
                eventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER),
                SensorManager.SENSOR_DELAY_NORMAL
        )
    }
}