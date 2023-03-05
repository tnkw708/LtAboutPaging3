package com.github.yescalibur.ltaboutpaging3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.github.yescalibur.ltaboutpaging3.R
import com.github.yescalibur.ltaboutpaging3.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<PokemonViewModel> { PokemonViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // アクティビティで以下の処理を追加する
        val pagingAdapter = PokemonAdapter()
        val recyclerView = binding.recyclerPokemon
        recyclerView.adapter = pagingAdapter

        lifecycleScope.launch{
            viewModel.pokemonFlow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }
}