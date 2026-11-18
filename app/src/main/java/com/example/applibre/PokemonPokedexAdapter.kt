
package com.example.applibre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.applibre.network.PokemonResumen

class PokemonPokedexAdapter(
    private val pokemons: List<PokemonResumen>,
    private val alPulsar: (PokemonResumen) -> Unit
) : RecyclerView.Adapter<PokemonPokedexAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.nombrePokemonApi)
        val imagen: ImageView = view.findViewById(R.id.imagenPokemonApi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.fila_pokemon_api, parent, false)

        return PokemonViewHolder(vista)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]

        holder.nombre.text = pokemon.name.replaceFirstChar { it.uppercase() }

        val idPokemon = pokemon.url.trimEnd('/').substringAfterLast('/')
        val urlImagen = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$idPokemon.png"

        Glide.with(holder.itemView).load(urlImagen).into(holder.imagen)

        // Esto lleva dentro de cada poemon para las estadisticas
        holder.itemView.setOnClickListener {
            alPulsar(pokemon)
        }
    }

    override fun getItemCount(): Int = pokemons.size
}
