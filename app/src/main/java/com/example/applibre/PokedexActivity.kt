package com.example.applibre

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applibre.network.PokemonResumen
import kotlinx.coroutines.launch

class PokedexActivity : AppCompatActivity() {

    private val viewModel: PokedexViewModel by viewModels()
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)

        recycler = findViewById(R.id.recyclerPokedex)
        val progreso = findViewById<ProgressBar>(R.id.progresoPokedex)
        val textoError = findViewById<TextView>(R.id.textoErrorPokedex)
        val botonVolver = findViewById<Button>(R.id.botonVolverPokedex)

        recycler.layoutManager = LinearLayoutManager(this)
        botonVolver.setOnClickListener { finish() }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.estado.collect { estado ->
                    progreso.visibility =
                        if (estado is EstadoLista.Cargando) View.VISIBLE else View.GONE

                    when (estado) {
                        is EstadoLista.Cargando -> textoError.visibility = View.GONE
                        is EstadoLista.Error -> {
                            textoError.text = "No se han podido cargar los Pokémon"
                            textoError.visibility = View.VISIBLE
                        }
                        is EstadoLista.Exito -> {
                            if (estado.sinConexion) {
                                textoError.text = "Sin conexión: mostrando los Pokémon guardados"
                                textoError.visibility = View.VISIBLE
                            } else {
                                textoError.visibility = View.GONE
                            }
                            mostrarLista(estado.pokemons)
                        }
                    }
                }
            }
        }
    }

    private fun mostrarLista(lista: List<PokemonResumen>) {
        recycler.adapter = PokemonPokedexAdapter(lista) { pokemon ->
            val id = pokemon.url.trimEnd('/').substringAfterLast('/').toInt()
            val intent = Intent(this, PokemonDetalleActivity::class.java)
            intent.putExtra("idPokemon", id)
            startActivity(intent)
        }
    }
}