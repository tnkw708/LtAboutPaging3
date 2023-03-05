package com.github.yescalibur.ltaboutpaging3.service

import com.github.yescalibur.ltaboutpaging3.model.MarsPhoto
import com.github.yescalibur.ltaboutpaging3.model.Pokemon
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET


private const val BASE_URL = "https://pokeapi.co/api/v2/"
private const val URL = "https://android-kotlin-fun-mars-server.appspot.com/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PokemonapiService {
    @GET("pokemon")
    suspend fun getPokemons(): List<Pokemon>
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}

object PokemonApi {
    val retrofitService: PokemonapiService by lazy { retrofit.create(PokemonapiService::class.java) }
}