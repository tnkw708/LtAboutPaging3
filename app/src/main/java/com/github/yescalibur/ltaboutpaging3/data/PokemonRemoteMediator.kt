package com.github.yescalibur.ltaboutpaging3.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.github.yescalibur.ltaboutpaging3.model.Pokemon

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(private val repository: PokemonRepository) : RemoteMediator<Int, Pokemon>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Pokemon>
    ): MediatorResult {
        val pokemonDao = repository.pokemonDao
        val key = when(loadType) {
            LoadType.REFRESH -> {
                null
            }
            LoadType.PREPEND -> {
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            LoadType.APPEND -> {
                state.anchorPosition?.let { anchorPosition ->
                    val anchorPage = state.closestPageToPosition(anchorPosition)
                    anchorPage?.prevKey ?: anchorPage?.nextKey
                }
            }
        }
        return try {
            val pokemonList =
                if(key == null) repository.getPokemons()
                else repository.getNextPokemons(key)

            pokemonDao.insertAll(pokemonList)

            MediatorResult.Success(endOfPaginationReached = pokemonList.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

}