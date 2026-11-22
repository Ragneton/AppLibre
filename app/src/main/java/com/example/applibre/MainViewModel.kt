package com.example.applibre

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.applibre.network.RetrofitClient
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repositorio = PokemonRepository(
        AppDatabase.obtener(application).pokemonDao(),
        RetrofitClient.api
    )

    fun guardar(nombre: String, tipo: String, habilidad: String) {
        viewModelScope.launch {
            repositorio.guardarEnHistorial(
                PokemonEntity(nombre = nombre, tipo = tipo, habilidad = habilidad)
            )
        }
    }
}