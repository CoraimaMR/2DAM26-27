package com.example.brujuladigital

import android.Manifest
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource

class MapaActivity : FragmentActivity(), OnMapReadyCallBack {
    private lateinit var txtUbicacion: TextView
    private lateinit var cliente: FusedLocationPrividerClient
    private var googleMap: GoogleMap? = null
    private var ultimaUbicacion: LatLng? = null
    private var cancelaccion: CancellationTokenSource? = null

    private val solicitarPermisos = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()) {
            permisos ->
        if(permisos[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
            permisos[Manifest.permission.ACCESS_COARSE_LOCATION] == true ){
            obtenerUbicacion()
        } else {
            txtUbicacion.text = "Permiso denegado, debes de activar los permisos de localización"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)

        txtUbicacion = findViewById(R.id.txtUbicacion)
    }
}