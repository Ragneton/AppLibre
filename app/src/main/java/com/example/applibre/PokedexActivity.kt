package com.example.applibre

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applibre.network.RetrofitClient
import kotlinx.coroutines.launch

class PokedexActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)

        val recycler = findViewById<RecyclerView>(R.id.recyclerPokedex)
        val progreso = findViewById<ProgressBar>(R.id.progresoPokedex)
        val textoError = findViewById<TextView>(R.id.textoErrorPokedex)
        val botonVolver = findViewById<Button>(R.id.botonVolverPokedex)

        recycler.layoutManager = LinearLayoutManager(this)

        botonVolver.setOnClickListener {
            finish()
        }

        lifecycleScope.launch {
            progreso.visibility = View.VISIBLE
            textoError.visibility = View.GONE

            try {
                val respuesta = RetrofitClient.api.obtenerPokemons(20, 0)

                recycler.adapter = PokemonPokedexAdapter(respuesta.results) { pokemon ->

                    val idPokemon = pokemon.url.trimEnd('/').substringAfterLast('/').toInt()

                    val intent = android.content.Intent(this@PokedexActivity, PokemonDetalleActivity::class.java)

                    intent.putExtra("idPokemon", idPokemon)

                    startActivity(intent)
                }

            } catch (e: Exception) {
                textoError.visibility = View.VISIBLE
            } finally {
                progreso.visibility = View.GONE
            }
        }
    }
}