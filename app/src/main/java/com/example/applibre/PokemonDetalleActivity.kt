package com.example.applibre

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.applibre.network.RetrofitClient
import kotlinx.coroutines.launch
import com.example.applibre.AppDatabase
import com.example.applibre.PokemonApiEntity

class PokemonDetalleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detalle)

        val textoNumero = findViewById<TextView>(R.id.textoNumero)
        val textoNombre = findViewById<TextView>(R.id.textoNombre)
        val textoTipos = findViewById<TextView>(R.id.textoTipos)
        val textoHabilidades = findViewById<TextView>(R.id.textoHabilidades)
        val textoAltura = findViewById<TextView>(R.id.textoAltura)
        val textoPeso = findViewById<TextView>(R.id.textoPeso)

        val imagen = findViewById<ImageView>(R.id.imagenDetalle)
        val progreso = findViewById<ProgressBar>(R.id.progresoDetalle)
        val textoError = findViewById<TextView>(R.id.textoErrorDetalle)
        val botonVolver = findViewById<Button>(R.id.botonVolverDetalle)

        botonVolver.setOnClickListener {
            finish()
        }

        val idPokemon = intent.getIntExtra("idPokemon", -1)

        if (idPokemon == -1) {
            textoError.visibility = View.VISIBLE
            return
        }

        lifecycleScope.launch {
            progreso.visibility = View.VISIBLE
            textoError.visibility = View.GONE

            try {
                val pokemon = RetrofitClient.api.obtenerDetalle(idPokemon)

                textoNumero.text = "N.º ${pokemon.id}"
                textoNombre.text = pokemon.nombre.replaceFirstChar {
                    it.uppercase()
                }

                val tipos = pokemon.tipo.joinToString(", ") {
                    it.tipo.nombre
                }

                textoTipos.text = "Tipos: $tipos"

                val habilidades = pokemon.abilidades.joinToString(", ") {
                    it.abilidad.nombre
                }

                textoHabilidades.text = "Habilidades: $habilidades"

                textoAltura.text = "Altura: ${pokemon.altura / 10.0} m"
                textoPeso.text = "Peso: ${pokemon.peso / 10.0} kg"

                Glide.with(this@PokemonDetalleActivity).load(pokemon.sprites.front_default).into(imagen)

                val pokemonGuardado = PokemonApiEntity(
                    id = pokemon.id,
                    nombre = pokemon.nombre,
                    imagen = pokemon.sprites.front_default,
                    tipos = tipos,
                    habilidades = habilidades,
                    altura = pokemon.altura,
                    peso = pokemon.peso
                )

                AppDatabase.obtener(this@PokemonDetalleActivity).pokemonDao().insertarPokemonApi(pokemonGuardado)

            } catch (e: Exception) {
                textoError.visibility = View.VISIBLE
            } finally {
                progreso.visibility = View.GONE
            }
        }
    }
}
