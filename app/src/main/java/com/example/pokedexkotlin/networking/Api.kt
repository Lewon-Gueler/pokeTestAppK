package com.example.pokedexkotlin.networking

import com.example.pokedexkotlin.Pokemon
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("pokemon")
    fun getAll(@Query("limit") limit: Int, @Query("offset") offset: Int): retrofit2.Call<PokemonData>

    @GET("pokemon/{id}")
    fun getPokemon(@Path("id")id: Int) :retrofit2.Call<Pokemon>

}