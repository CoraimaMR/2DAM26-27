package com.example.sporttracking

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // !!!!

        val edtUsuario = findViewById<EditText>(R.id.edtUsuario)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)

        val btnEntrar = findViewById<Button>(R.id.btnEntrar)
        val btnCancelar = findViewById<Button>(R.id.btnCancelar)

        val btnSegundaPantalla = findViewById<Button>(R.id.btnSegundaPantalla)
        btnSegundaPantalla.setOnClickListener {
            val intent = Intent(
                this,
                SegundaActivity::class.java
            )
            startActivity(intent)
        }

        btnEntrar.setOnClickListener {
            val usuario = edtUsuario.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            if (usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    "Debes de completar todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val intent = Intent(
                    this,
                    SegundaActivity::class.java
                )
                intent.putExtra("USUARIO", usuario)
                startActivity(intent)
            }
        }
    }
}