package com.github.yescalibur.ltaboutpaging3.ui

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.yescalibur.ltaboutpaging3.databinding.ItemPokemonBinding
import com.github.yescalibur.ltaboutpaging3.model.Pokemon

class PokemonAdapter: PagingDataAdapter<Pokemon, PokemonViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val itemPokemon = getItem(position) ?: return
        holder.bind(itemPokemon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        PokemonViewHolder.create(parent)
}

class PokemonViewHolder(private val pokemonBinding: ItemPokemonBinding) :
RecyclerView.ViewHolder(pokemonBinding.root) {
    fun bind(pokemon: Pokemon) {
        pokemonBinding.imageView.load(pokemon.url)
    }

    companion object {
        fun create(parent: ViewGroup): PokemonViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val pokemonBinding = ItemPokemonBinding.inflate(layoutInflater, parent, false)
            return PokemonViewHolder(pokemonBinding)
        }
    }
}

private val diffCallback = object : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean
    = oldItem.javaClass == newItem.javaClass

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
        oldItem == newItem
}