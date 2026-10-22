package com.example.applibre

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private var reproductor: MediaPlayer? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val textoResultado = findViewById<TextView>(R.id.textoResultado)
        val imagenResultado = findViewById<ImageView>(R.id.imgResultado)
        val botonVolver = findViewById<Button>(R.id.botonVolver)

        val nombrePok = intent.getStringExtra("Nombre_guardado") ?: ""
        val tipoPok = intent.getStringExtra("Tipo")

        textoResultado.text = "El pokemon:, $nombrePok, es de tipo"


    }
}