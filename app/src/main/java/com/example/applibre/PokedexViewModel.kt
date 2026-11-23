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

class PokedexViewModel(application: Application) : AndroidViewModel(application) {

    private val repositorio = PokemonRepository(
        AppDatabase.obtener(application).pokemonDao(),
        RetrofitClient.api
    )

    private val _estado = MutableStateFlow<EstadoLista>(EstadoLista.Cargando)
    val estado: StateFlow<EstadoLista> = _estado

    init {
        cargar()
    }

    fun cargar() {
        _estado.value = EstadoLista.Cargando
        viewModelScope.launch {
            _estado.value = try {
                val r = repositorio.obtenerLista()
                EstadoLista.Exito(r.datos, r.sinConexion)
            } catch (e: Exception) {
                EstadoLista.Error
            }
        }
    }

    // Mejora, esto hace que cuando se narque la casilla de favoritos solo se muestren los favoritos
    fun mostrarFavoritos(soloFavoritos: Boolean) {
        if (!soloFavoritos) {
            cargar()
            return
        }
        _estado.value = EstadoLista.Cargando
        viewModelScope.launch {
            _estado.value = EstadoLista.Exito(repositorio.obtenerFavoritos(), sinConexion = false)
        }
    }
}