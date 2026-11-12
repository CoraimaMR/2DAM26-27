package com.example.sporttracking


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity

class SegundaActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)

        val txtbienvenida = findViewById<TextView>(R.id.txtBienvenida)
        val usuario = intent.getStringExtra("USUARIO")

        if (usuario.isNullOrEmpty()){
            txtbienvenida.text = "Debes de logear un usuario"
        } else {
            txtbienvenida.text = "Bienvenid@, $usuario"
        }
    }
}