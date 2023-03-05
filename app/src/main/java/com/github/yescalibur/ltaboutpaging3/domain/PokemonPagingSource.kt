package com.github.yescalibur.ltaboutpaging3.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.yescalibur.ltaboutpaging3.data.PokemonRepository
import com.github.yescalibur.ltaboutpaging3.model.Pokemon

class PokemonPagingSource(private val repository: PokemonRepository):
    PagingSource<Int, Pokemon>() {
    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey ?: anchorPage?.nextKey
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val currentKey = params.key ?: 1



        try {
            val pokemonList =
                if(currentKey == 1) repository.getPokemons()
                else repository.getNextPokemons(currentKey)

            return LoadResult.Page(
                data = pokemonList,
                prevKey = currentKey,
                nextKey = currentKey.plus(1),
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}