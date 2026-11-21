package com.example.applibre

import com.example.applibre.network.PokemonApi
import com.example.applibre.network.PokemonResumen

// Los datos, y si vienen de Room porque no hubo conexión
data class Resultado<T>(val datos: T, val sinConexion: Boolean)

class PokemonRepository(
    private val dao: PokeDAO,
    private val api: PokemonApi
) {

    suspend fun obtenerLista(): Resultado<List<PokemonResumen>> {
        return try {
            Resultado(api.obtenerPokemons(20, 0).results, sinConexion = false)
        } catch (e: Exception) {
            val guardados = dao.obtenerTodos().map {
                PokemonResumen(it.nombre, "https://pokeapi.co/api/v2/pokemon/${it.id}/")
            }
            if (guardados.isEmpty()) throw e      // no hay nada que enseñar
            Resultado(guardados, sinConexion = true)
        }
    }

    suspend fun obtenerDetalle(id: Int): Resultado<PokemonApiEntity> {
        return try {
            val p = api.obtenerDetalle(id)
            val entidad = PokemonApiEntity(
                id = p.id,
                nombre = p.nombre,
                imagen = p.sprites.front_default,
                tipos = p.tipo.joinToString(", ") { it.tipo.nombre },
                habilidades = p.abilidades.joinToString(", ") { it.abilidad.nombre },
                altura = p.altura,
                peso = p.peso
            )
            dao.insertarPokemonApi(entidad)
            Resultado(entidad, sinConexion = false)
        } catch (e: Exception) {
            val guardado = dao.obtenerPokemonApi(id) ?: throw e
            Resultado(guardado, sinConexion = true)
        }
    }
}