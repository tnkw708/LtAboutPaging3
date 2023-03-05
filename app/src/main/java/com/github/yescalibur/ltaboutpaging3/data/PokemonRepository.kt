package com.github.yescalibur.ltaboutpaging3.data

import androidx.room.Room
import com.github.yescalibur.ltaboutpaging3.db.PokemonDatabase
import com.github.yescalibur.ltaboutpaging3.model.Pokemon
import com.github.yescalibur.ltaboutpaging3.service.PokemonApi


class PokemonRepository(database: PokemonDatabase) {
    val pokemonDao = database.getPokemonDao()
    private val pokemon = Pokemon()

    suspend fun getPokemons(): List<Pokemon> {
        pokemonDao.getPagingSource()
        pokemonDao.insertAll(listOf(pokemon))
        pokemonDao.deleteAll()
       return PokemonApi.retrofitService.getPokemons()
    }

    suspend fun getNextPokemons(key: Int): List<Pokemon> {
        return PokemonApi.retrofitService.getPokemons()
    }
}