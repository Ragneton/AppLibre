package com.example.applibre

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.applibre.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistorialViewModel(application: Application) : AndroidViewModel(application) {

    private val repositorio = PokemonRepository(
        AppDatabase.obtener(application).pokemonDao(),
        RetrofitClient.api
    )

    private val _pokemons = MutableStateFlow<List<PokemonEntity>>(emptyList())
    val pokemons: StateFlow<List<PokemonEntity>> = _pokemons

    fun cargar() {
        viewModelScope.launch {
            _pokemons.value = repositorio.obtenerHistorial()
        }
    }

    fun borrar(pokemon: PokemonEntity) {
        viewModelScope.launch {
            repositorio.borrarDelHistorial(pokemon)
            cargar()
        }
    }
}