package com.example.applibre.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokeDAO {
    @Insert
    suspend fun insertar(pokemon: PokemonEntity)

    @Query("SELECT * FROM pokemons ORDER BY id DESC")
    suspend fun obtenerTodos(): List<PokemonEntity>

    @Delete
    suspend fun borrar(pokemon: PokemonEntity)

    // Pokémon descargados desde la PokéAPI
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarPokemonApi(pokemon: PokemonApiEntity)

    @Query("SELECT * FROM pokemons_api WHERE id = :id")
    suspend fun obtenerPokemonApi(id: Int): PokemonApiEntity?

    @Query("SELECT * FROM pokemons_api ORDER BY id")
    suspend fun obtenerTodosApi(): List<PokemonApiEntity>

    // Parte de la mejora, Favorito
    // Marcar o desmarcar un Pokémon como favorito
    @Query("UPDATE pokemons_api SET esFavorito = :favorito WHERE id = :id")
    suspend fun marcarFavorito(id: Int, favorito: Boolean)

    // Dame solo los Pokémon marcados como favoritos
    @Query("SELECT * FROM pokemons_api WHERE esFavorito = 1 ORDER BY id")
    suspend fun obtenerFavoritos(): List<PokemonApiEntity>
}