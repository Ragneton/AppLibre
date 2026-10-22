package com.example.applibre

data class Pokemon(val nombre: String, val tipo: String)

object Pokedex {
    val pokemons = mutableListOf<Pokemon>()
}