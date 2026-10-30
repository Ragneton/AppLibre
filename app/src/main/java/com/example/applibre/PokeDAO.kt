package com.example.applibre

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PokeDAO {
    @Insert
    suspend fun insertar(nota: NotaPok)

    @Query("SELECT * FROM pokemons ORDER BY id DESC")
    suspend fun obtenerTodas(): List<NotaPok>

    @Delete
    suspend fun borrar(nota: NotaPok)
}