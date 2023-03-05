package com.github.yescalibur.ltaboutpaging3

import android.app.Application
import androidx.room.Room
import com.github.yescalibur.ltaboutpaging3.db.PokemonDatabase

class MyApplication : Application() {
    companion object {
        lateinit var db : PokemonDatabase
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            PokemonDatabase::class.java,
            "pokemon_db"
        ).build()
    }
}