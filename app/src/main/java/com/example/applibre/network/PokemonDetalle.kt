package com.example.applibre.network

data class PokemonDetalle(
    val id: Int,
    val nombre: String,
    val altura: Int,
    val peso: Int,
    val tipo: List<TipoPokemon>,
    val abilidades: List<HabilidadPokemon>,
    val sprites: SpritesPokemon
)

data class TipoPokemon(
    val tipo: NombrePokemon
)

data class HabilidadPokemon(
    val abilidad: NombrePokemon
)

data class NombrePokemon(
    val nombre: String
)

data class SpritesPokemon(
    val front_default: String?
)