package com.example.applibre

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.applibre.data.AppDatabase
import com.example.applibre.data.PokemonRepository
import com.example.applibre.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonDetalleViewModel(application: Application) : AndroidViewModel(application) {

    private val repositorio = PokemonRepository(
        AppDatabase.obtener(application).pokemonDao(),
        RetrofitClient.api
    )

    private val _estado = MutableStateFlow<EstadoDetalle>(EstadoDetalle.Cargando)
    val estado: StateFlow<EstadoDetalle> = _estado

    fun cargar(id: Int) {
        _estado.value = EstadoDetalle.Cargando
        viewModelScope.launch { _estado.value = try {
                val r = repositorio.obtenerDetalle(id)
                EstadoDetalle.Exito(r.datos, r.sinConexion)
            } catch (e: Exception) {
                EstadoDetalle.Error
            }
        }
    }

    // Funcion para alternar el favorito
    fun alternarFavorito() {
        val actual = _estado.value as? EstadoDetalle.Exito ?: return
        val nuevo = !actual.pokemon.esFavorito

        viewModelScope.launch {
            repositorio.cambiarFavorito(actual.pokemon.id, nuevo)
            _estado.value = actual.copy(pokemon = actual.pokemon.copy(esFavorito = nuevo))
        }
    }
}