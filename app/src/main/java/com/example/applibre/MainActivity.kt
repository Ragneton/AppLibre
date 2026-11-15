package com.example.applibre

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editNombre = findViewById<EditText>(R.id.textoEditableNombre)
        val editHabilidad = findViewById<EditText>(R.id.textoEditableHabilidad)
        val listaTipos = findViewById<Spinner>(R.id.listaTipos)
        val btnPokedex = findViewById<Button>(R.id.botonPokedex)
        val btnHistorial = findViewById<Button>(R.id.botonHistorial)

        val dao = AppDatabase.obtener(this).pokemonDao()

        val prefs = getSharedPreferences("Nombres", MODE_PRIVATE)
        editNombre.setText(prefs.getString("Nombre_guardado", ""))

        btnPokedex.setOnClickListener {
            val nombre = editNombre.text.toString().trim()
            val habilidad = editHabilidad.text.toString().trim()
            val tipo = listaTipos.selectedItem.toString()

            if (nombre.isEmpty()) {
                Toast.makeText(this, "Escribe el nombre del pokemon para seguir", Toast.LENGTH_SHORT).show()
            } else if (habilidad.isEmpty()) {
                Toast.makeText(this, "Escribe la habilidad del pokemon", Toast.LENGTH_SHORT).show()
            } else {
                prefs.edit().putString("Nombre_guardado", nombre).apply()

                lifecycleScope.launch {
                    dao.insertar(PokemonEntity(nombre = nombre, tipo = tipo, habilidad = habilidad))
                }

                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("Nombre", nombre)
                intent.putExtra("Tipo", tipo)
                intent.putExtra("Habilidad", habilidad)
                startActivity(intent)
            }
        }

        btnHistorial.setOnClickListener {
            startActivity(Intent(this, HistorialActivity::class.java))
        }
    }
}