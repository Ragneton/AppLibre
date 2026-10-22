package com.example.applibre

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editNombre = findViewById<EditText>(R.id.textoEditableNombre)
        val listaTipos = findViewById<Spinner>(R.id.listaTipos)
        val btnPokedex = findViewById<Button>(R.id.botonPokedex)
        val btnFondo = findViewById<Button>(R.id.botonFondo)
        val layoutPrincipal = findViewById<LinearLayout>(R.id.layoutPrincipal)

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

                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("Nombre", nombre)
                intent.putExtra("Tipo", tipo)
                startActivity(intent)
            }
        }

        btnFondo.setOnClickListener {
            //Esto hace que la imagen del fondo se pueda oscurecer o hacer transparente segun el parametro que le meta
            val fondo = androidx.core.content.ContextCompat.getDrawable(this, R.drawable.primera_gen)
            fondo?.alpha = 20        // 0 = invisible, 255 = opaca
            layoutPrincipal.background = fondo
        }
    }
}