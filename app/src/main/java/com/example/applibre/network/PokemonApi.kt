package com.example.applibre.network

import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun obtenerPokemons(
        @Query("limit") limite: Int,
        @Query("offset") desplazamiento: Int
    ): PokemonListResponse

}
