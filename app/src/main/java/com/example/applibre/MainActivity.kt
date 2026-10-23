package com.example.applibre

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.java


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editNombre = findViewById<EditText>(R.id.textoEditableNombre)
        val listaTipos = findViewById<Spinner>(R.id.listaTipos)
        val btnPokedex = findViewById<Button>(R.id.botonPokedex)
        val btnHistorial = findViewById<Button>(R.id.botonHistorial)

        val prefs = getSharedPreferences("Nombres", MODE_PRIVATE)

        val nombreGuardado = prefs.getString("Nombre_guardado", "")
        editNombre.setText(nombreGuardado)

        btnPokedex.setOnClickListener {
            val nombre = editNombre.text.toString()
            val tipo = listaTipos.selectedItem.toString()

            if (nombre.isEmpty()) {
                Toast.makeText(this, "Escribe el nombre del pokemon para seguir", Toast.LENGTH_SHORT).show()
            } else {
                prefs.edit().putString("Nombre_guardado", nombre).apply()

                Pokedex.pokemons.add(Pokemon(nombre, tipo, imagenDelTipo(tipo)))

                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("Nombre", nombre)
                intent.putExtra("Tipo", tipo)
                startActivity(intent)
            }
        }

        btnHistorial.setOnClickListener {
            startActivity(Intent(this, HistorialActivity::class.java))
        }
    }
}