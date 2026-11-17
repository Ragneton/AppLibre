package com.example.applibre.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // Conectamos la app con la API de pokemon
    private const val API_URL = "https://pokeapi.co/api/v2/"

    val api: PokemonApi by lazy {
        Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build().create(PokemonApi::class.java)
    }
}
