package com.example.applibre

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private var reproductor: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textoResultado = findViewById<TextView>(R.id.textoResultado)
        val imagenResultado = findViewById<ImageView>(R.id.imgResultado)
        val botonVolver = findViewById<Button>(R.id.botonVolver)

        val nombrePok = intent.getStringExtra("Nombre") ?: ""
        val tipoPok = intent.getStringExtra("Tipo")

        textoResultado.text = "El pokemon $nombrePok es de tipo $tipoPok"

        val sonidoId: Int
        when {
            tipoPok == "Normal" -> {
               imagenResultado.setImageResource(R.drawable.normal)
                sonidoId = R.raw.snorlax
            }

            tipoPok == "Fuego" -> {
                imagenResultado.setImageResource(R.drawable.fuego)
                sonidoId = R.raw.charizard
            }

            tipoPok == "Agua" -> {
                imagenResultado.setImageResource(R.drawable.agua)
                sonidoId = R.raw.squirtle
            }

            tipoPok == "Planta" -> {
                imagenResultado.setImageResource(R.drawable.planta)
                sonidoId = R.raw.bulbasaur
            }

            tipoPok == "Eléctrico" -> {
                imagenResultado.setImageResource(R.drawable.electrico)
                sonidoId = R.raw.pikachu
            }

            tipoPok == "Hielo" -> {
                imagenResultado.setImageResource(R.drawable.hielo)
                sonidoId = R.raw.glaceon_cry
            }

            tipoPok == "Lucha" -> {
                imagenResultado.setImageResource(R.drawable.lucha)
                sonidoId = R.raw.lucario
            }

            tipoPok == "Veneno" -> {
                imagenResultado.setImageResource(R.drawable.veneno)
                sonidoId = R.raw.ekans
            }

            tipoPok == "Tierra" -> {
                imagenResultado.setImageResource(R.drawable.tierra)
                sonidoId = R.raw.cubone
            }

            tipoPok == "Volador" -> {
                imagenResultado.setImageResource(R.drawable.volador)
                sonidoId = R.raw.pidgeot
            }

            tipoPok == "Psíquico" -> {
                imagenResultado.setImageResource(R.drawable.psiquico)
                sonidoId = R.raw.gardevoir
            }

            tipoPok == "Bicho" -> {
                imagenResultado.setImageResource(R.drawable.bicho)
                sonidoId = R.raw.caterpie
            }

            tipoPok == "Roca" -> {
                imagenResultado.setImageResource(R.drawable.roca)
                sonidoId = R.raw.geodude
            }

            tipoPok == "Fantasma" -> {
                imagenResultado.setImageResource(R.drawable.fantasma)
                sonidoId = R.raw.gengar
            }

            tipoPok == "Dragón" -> {
                imagenResultado.setImageResource(R.drawable.dragon)
                sonidoId = R.raw.garchomp
            }
            else -> {
                imagenResultado.setImageResource(R.drawable.dragon)
                sonidoId = R.raw.glaceon_cry
            }
        }

        reproductor = MediaPlayer.create(this, sonidoId)
        reproductor?.start()

        botonVolver.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        reproductor?.release()
        reproductor = null
    }
}