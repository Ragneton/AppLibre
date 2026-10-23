package com.example.applibre

data class Pokemon(val nombre: String, val tipo: String, val imagen: Int)

object Pokedex {
    val pokemons = mutableListOf<Pokemon>()
}

fun imagenDelTipo(tipo: String): Int = when (tipo) {
    "Normal" -> R.drawable.normal
    "Fuego" -> R.drawable.fuego
    "Agua" -> R.drawable.agua
    "Planta" -> R.drawable.planta
    "Eléctrico" -> R.drawable.electrico
    "Hielo" -> R.drawable.hielo
    "Lucha" -> R.drawable.lucha
    "Veneno" -> R.drawable.veneno
    "Tierra" -> R.drawable.tierra
    "Volador" -> R.drawable.volador
    "Psíquico" -> R.drawable.psiquico
    "Bicho" -> R.drawable.bicho
    "Roca" -> R.drawable.roca
    "Fantasma" -> R.drawable.fantasma
    "Dragón" -> R.drawable.dragon
    else -> R.drawable.normal
}