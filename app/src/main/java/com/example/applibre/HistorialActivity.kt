package com.example.applibre

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class HistorialActivity : AppCompatActivity() {

    private val viewModel: HistorialViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        val recycler = findViewById<RecyclerView>(R.id.recyclerHistorial)
        val btnVolver = findViewById<Button>(R.id.botonVolverHistorial)

        recycler.layoutManager = LinearLayoutManager(this)
        btnVolver.setOnClickListener { finish() }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pokemons.collect { lista ->
                    recycler.adapter = HistorialAdapter(lista) { pokemon ->
                        viewModel.borrar(pokemon)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.cargar()
    }
}