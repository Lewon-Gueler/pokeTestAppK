package com.example.pokedexkotlin.networking

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("pokemon")
    fun getAll(@Query("limit") limit: Int, @Query("offset") offset: Int): retrofit2.Call<List<PokemonData>>

}