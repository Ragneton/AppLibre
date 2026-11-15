package com.example.applibre

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class HistorialActivity : AppCompatActivity() {
    private lateinit var recycler: RecyclerView
    private lateinit var dao: PokeDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        recycler = findViewById(R.id.recyclerHistorial)
        val btnVolver = findViewById<Button>(R.id.botonVolverHistorial)
        dao = AppDatabase.obtener(this).pokemonDao()

        recycler.layoutManager = LinearLayoutManager(this)
        btnVolver.setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
        cargarLista()
    }

    private fun cargarLista() {
        lifecycleScope.launch {
            val lista = dao.obtenerTodas()
            recycler.adapter = HistorialAdapter(lista) { pokemon ->
                lifecycleScope.launch {
                    dao.borrar(pokemon)
                    cargarLista()
                }
            }
        }
    }
}