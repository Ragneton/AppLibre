package com.example.applibre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView

class HistorialAdapter(private val pokemons: List<Pokemon>) :
    RecyclerView.Adapter<HistorialAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textoNombre: TextView = view.findViewById(R.id.textoNombreFila)
        val textoTipo: TextView = view.findViewById(R.id.textoTipoFila)
        val imagen: ImageView = view.findViewById(R.id.imagenFila)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.fila_pokemon, parent, false)
        return PokemonViewHolder(vista)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.textoNombre.text = pokemon.nombre
        holder.textoTipo.text = pokemon.tipo
        holder.imagen.setImageResource(pokemon.imagen)
    }

    override fun getItemCount(): Int = pokemons.size
}