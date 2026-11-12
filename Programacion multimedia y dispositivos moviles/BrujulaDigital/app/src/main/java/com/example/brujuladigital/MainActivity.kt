package com.example.brujuladigital

import android.app.Activity
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.roundToInt
import android.util.Log

/** Ejemplo didáctico de brújula: teléfono horizontal, pantalla hacia arriba. */
class MainActivity : Activity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var acelerometro: Sensor? = null
    private var magnetometro: Sensor? = null

    // Últimas lecturas recibidas de cada sensor (X, Y, Z).
    private val gravedad = FloatArray(3)
    private val campoMagnetico = FloatArray(3)
    private var tieneGravedad = false
    private var tieneMagnetismo = false

    private lateinit var txtGrados: TextView
    private lateinit var txtDireccion: TextView
    private lateinit var txtSensor: TextView
    private lateinit var imgBrujula: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtGrados = findViewById(R.id.txtGrados)
        txtDireccion = findViewById(R.id.txtDireccion)
        txtSensor = findViewById(R.id.txtSensor)
        imgBrujula = findViewById(R.id.imgBrujula)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        magnetometro = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        if (acelerometro == null || magnetometro == null) {
            txtSensor.text = "Falta acelerómetro o magnetómetro"
            txtDireccion.text = "Brújula no disponible"
        } else {
            txtSensor.text = "Sensores disponibles. Gira el teléfono."
        }
    }

    override fun onResume() {
        super.onResume()
        acelerometro?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
        magnetometro?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {

                System.arraycopy(event.values, 0, gravedad, 0, 3)

                tieneGravedad = true
            }

            Sensor.TYPE_MAGNETIC_FIELD -> {

                System.arraycopy(event.values, 0, campoMagnetico, 0, 3)

                tieneMagnetismo = true
            }
        }
        Log.d("BRÚJULA,", "aCELERÓMETRO: X=${gravedad[0]} Y=${gravedad[1]} Z=${gravedad[2]}")
        Log.d(
            "BRÚJULA,",
            "Magenetómetro: X=${campoMagnetico[0]} Y=${campoMagnetico[1]} Z=${campoMagnetico[2]}"
        )
        if (tieneGravedad && tieneMagnetismo) calcularOrientacion()
    }

    private fun calcularOrientacion() {
        val matrizRotacion = FloatArray(9)

        val orientacion = FloatArray(3)

        val resultado =
            SensorManager.getRotationMatrix(matrizRotacion, null, gravedad, campoMagnetico)

        if (!resultado) {
            return;
        }

        SensorManager.getOrientation(matrizRotacion, orientacion)

        val azimutGrados = Math.toDegrees(orientacion[0].toDouble()).toFloat()

        val grados = (azimutGrados + 360f) % 360f

        txtGrados.text = "${grados.roundToInt() % 360} º"

        txtDireccion.text = obtenerPuntoCardinal(grados)

    }

    private fun obtenerPuntoCardinal(grados: Float): String {
        return when {
            grados < 90f -> "Norte y el Noreste"
            grados < 180f -> "este y Sureste"
            grados < 270f -> "Sur y el suroeste"
            else -> "Oeste y Noroeste"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }
}