package com.example.applibre.network

import com.google.gson.annotations.SerializedName

data class PokemonDetalle(
    val id: Int,
    @SerializedName("name") val nombre: String,
    @SerializedName("height") val altura: Int,
    @SerializedName("weight") val peso: Int,
    @SerializedName("types") val tipo: List<TipoPokemon>,
    @SerializedName("abilities") val abilidades: List<HabilidadPokemon>,
    val sprites: SpritesPokemon
)

data class TipoPokemon(
    @SerializedName("type") val tipo: NombrePokemon
)

data class HabilidadPokemon(
    @SerializedName("ability") val abilidad: NombrePokemon
)

data class NombrePokemon(
    @SerializedName("name") val nombre: String
)

data class SpritesPokemon(
    @SerializedName("front_default") val front_default: String?
)