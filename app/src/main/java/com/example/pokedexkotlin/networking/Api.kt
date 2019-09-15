package com.example.pokedexkotlin.networking

import com.example.pokedexkotlin.Pokemon
import com.example.pokedexkotlin.PokemonData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface Api {

    @GET("pokemon")
    fun getAll(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<PokemonList>

    @GET
    fun getPokeWithURL(@Url url: String) : Call<PokemonData>

    @GET("pokemon/{id}")
    fun getPokemon(@Path("id")id: Int) :Call<Pokemon>

    @GET
    fun get(): retrofit2.Call<PokemonData>

}