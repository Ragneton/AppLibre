package com.example.applibre

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
    suspend fun obtenerTodas(): List<PokemonEntity>

    @Delete
    suspend fun borrar(pokemon: PokemonEntity)

    // Pokémon descargados de PokéAPI
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarPokemonApi(pokemon: PokemonApiEntity)

    @Query("SELECT * FROM pokemons_api WHERE id = :id")
    suspend fun obtenerPokemonApi(id: Int): PokemonApiEntity?
}
}