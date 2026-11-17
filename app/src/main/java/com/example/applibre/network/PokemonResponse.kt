package com.example.applibre.network

data class PokemonListResponse(
    val count: Int,
    val results: List<PokemonResumen>
)

data class PokemonResumen(
    val name: String,
    val url: String
)