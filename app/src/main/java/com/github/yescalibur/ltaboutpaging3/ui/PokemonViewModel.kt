package com.github.yescalibur.ltaboutpaging3.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.github.yescalibur.ltaboutpaging3.MyApplication
import com.github.yescalibur.ltaboutpaging3.data.PokemonRemoteMediator
import com.github.yescalibur.ltaboutpaging3.data.PokemonRepository
import com.github.yescalibur.ltaboutpaging3.db.PokemonDatabase
import com.github.yescalibur.ltaboutpaging3.domain.PokemonPagingSource
import com.github.yescalibur.ltaboutpaging3.model.MarsPhoto
import com.github.yescalibur.ltaboutpaging3.model.Pokemon
import com.github.yescalibur.ltaboutpaging3.service.PokemonApi
import com.github.yescalibur.ltaboutpaging3.service.PokemonapiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(private val repository: PokemonRepository) : ViewModel() {
    private val _pokemonState = MutableStateFlow<List<Pokemon>>(emptyList())
    private val _photoState = MutableStateFlow(emptyList<MarsPhoto>())

    // 今回はviewModel内でFlowで受け取る
//    val pokemonFlow = Pager(
//        PagingConfig(
//            pageSize = 20,
//            prefetchDistance = 20,
//            enablePlaceholders = false)) {
//        PokemonPagingSource(repository)
//    }.flow
    val pokemonDao = repository.pokemonDao


    init {
        getPokemons()
        Log.d("test1", _photoState.value.toString())
    }

    @OptIn(ExperimentalPagingApi::class)
    private fun getPokemons() {

        viewModelScope.launch {
            val pokemonPager: Pager<Int, Pokemon> =
                Pager(
                    config = PagingConfig(10),
                    remoteMediator = PokemonRemoteMediator(repository),
                    pagingSourceFactory = { pokemonDao.getPagingSource() }
                    )
        }

    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val db = MyApplication.db
                val repository = PokemonRepository(db)
                PokemonViewModel(repository)
            }
        }
    }
}