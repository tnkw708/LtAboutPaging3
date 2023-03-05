package com.github.yescalibur.ltaboutpaging3.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.yescalibur.ltaboutpaging3.model.Pokemon

@Database(
    entities = [ Pokemon::class ],
    version = 1,
    exportSchema = false,
)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun getPokemonDao(): PokemonDao
}