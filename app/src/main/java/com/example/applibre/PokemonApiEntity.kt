package com.example.applibre

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons_api")
data class PokemonApiEntity(

    @PrimaryKey
    val id: Int,

    val nombre: String,
    val imagen: String?,
    val tipos: String,
    val habilidades: String,
    val altura: Int,
    val peso: Int
)
