package com.example.applibre

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val editNombre = findViewById<EditText>(R.id.textoEditableNombre)
        val listaTipos = findViewById<Spinner>(R.id.listaTipos)
        val btnPokedex = findViewById<Button>(R.id.botonPokedex)
        val btnFondo = findViewById<Button>(R.id.botonFondo)

    }
}