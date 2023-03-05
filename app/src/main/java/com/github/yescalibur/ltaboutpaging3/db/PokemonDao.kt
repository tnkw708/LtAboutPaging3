package com.github.yescalibur.ltaboutpaging3.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.yescalibur.ltaboutpaging3.model.Pokemon

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemons: List<Pokemon>)

    @Query("SELECT * FROM pokemon")
    fun getPagingSource(): PagingSource<Int, Pokemon>

    @Query("DELETE FROM pokemon")
    suspend fun deleteAll()
}