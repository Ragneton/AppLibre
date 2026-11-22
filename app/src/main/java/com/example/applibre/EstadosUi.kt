package com.example.applibre

import com.example.applibre.network.PokemonResumen

// Una sealed class es una lista cerrada de cualquier situacion posible
// Así la pantalla solo tiene que reaccionar a "cargando", "éxito" o "error".
sealed class EstadoLista {
    object Cargando : EstadoLista()
    data class Exito(val pokemons: List<PokemonResumen>, val sinConexion: Boolean) : EstadoLista()
    object Error : EstadoLista()
}

sealed class EstadoDetalle {
    object Cargando : EstadoDetalle()
    data class Exito(val pokemon: PokemonApiEntity, val sinConexion: Boolean) : EstadoDetalle()
    object Error : EstadoDetalle()
}