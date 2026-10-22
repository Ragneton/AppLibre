package com.example.applibre

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistorialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        val recycler = findViewById<RecyclerView>(R.id.recyclerHistorial)
        val btnVolver = findViewById<Button>(R.id.botonVolverHistorial)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = HistorialAdapter(Pokedex.pokemons)

        btnVolver.setOnClickListener {
            finish()
        }
    }
}