package com.example.lotr.logic.network

import com.example.lotr.logic.database.AppDatabase
import com.example.lotr.logic.entity.CharacterJSON
import com.example.lotr.logic.entity.MovieJSON
import com.example.lotr.logic.entity.QuoteJSON
import com.example.lotr.logic.repository.MovieWatchlistRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL = "https://the-one-api.dev/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface LOTRApiService {

    @GET("movie/")
    suspend fun getMovies(
        @Header("Authorization") authToken: String
    ): MovieJSON

    @GET("character/")
    suspend fun getCharacters(
        @Query("race") filter: String,
        @Header("Authorization") authToken: String
    ): CharacterJSON

    @GET("quote/")
    suspend fun getQuotes(
        @Header("Authorization") authToken: String
    ): QuoteJSON
}

object LOTRApi {
    val retrofitService : LOTRApiService by lazy {
        retrofit.create(LOTRApiService::class.java) }
}

enum class CharacterApiFilter(val value: String) {
    SHOW_ELVES("Elf"),
    SHOW_DWARVES("Dwarf"),
    SHOW_HOBBITS("Hobbit"),
    SHOW_HUMANS("Human"),
    SHOW_ALL("")}