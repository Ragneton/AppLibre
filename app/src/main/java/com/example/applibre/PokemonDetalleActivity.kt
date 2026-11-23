package com.example.applibre

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch

class PokemonDetalleActivity : AppCompatActivity() {

    private val viewModel: PokemonDetalleViewModel by viewModels()

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
        val botonFavorito = findViewById<Button>(R.id.botonFavorito)

        botonFavorito.setOnClickListener {
            viewModel.alternarFavorito()
        }

        botonVolver.setOnClickListener {
            finish()
        }

        val idPokemon = intent.getIntExtra("idPokemon", -1)
        if (idPokemon == -1) {
            textoError.visibility = View.VISIBLE
            return
        }

        if (savedInstanceState == null) viewModel.cargar(idPokemon)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.estado.collect { estado ->
                    progreso.visibility =
                        if (estado is EstadoDetalle.Cargando) View.VISIBLE else View.GONE

                    when (estado) {
                        is EstadoDetalle.Cargando -> textoError.visibility = View.GONE
                        is EstadoDetalle.Error -> {
                            textoError.text = "No se han podido cargar los datos"
                            textoError.visibility = View.VISIBLE
                        }
                        is EstadoDetalle.Exito -> {
                            val p = estado.pokemon
                            if (estado.sinConexion) {
                                textoError.text = "Sin conexión: mostrando datos guardados"
                                textoError.visibility = View.VISIBLE
                            } else {
                                textoError.visibility = View.GONE
                            }
                            textoNumero.text = "N.º ${p.id}"
                            textoNombre.text = p.nombre.replaceFirstChar { it.uppercase() }
                            textoTipos.text = "Tipos: ${p.tipos}"
                            textoHabilidades.text = "Habilidades: ${p.habilidades}"
                            textoAltura.text = "Altura: ${p.altura / 10.0} m"
                            textoPeso.text = "Peso: ${p.peso / 10.0} kg"
                            Glide.with(this@PokemonDetalleActivity).load(p.imagen).into(imagen)

                            // Mejora
                            botonFavorito.visibility = View.VISIBLE
                            botonFavorito.text =
                                if (p.esFavorito) "★ Quitar de favoritos" else "☆ Añadir a favoritos"
                        }
                    }
                }
            }
        }
    }
}