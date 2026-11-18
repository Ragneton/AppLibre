package com.example.applibre.network

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon")
    suspend fun obtenerPokemons(
        @Query("limit") limite: Int,
        @Query("offset") desplazamiento: Int
    ): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun obtenerDetalle(
        @Path("id") id: Int
    ): PokemonDetalle
}
