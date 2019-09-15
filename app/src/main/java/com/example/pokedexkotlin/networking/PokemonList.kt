package com.example.pokedexkotlin.networking

import com.example.pokedexkotlin.Pokemon
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class PokemonList (
    val count: Int? = null,
    val next: String? = null,
    val previos: String? = null,
    val results: List<Pokemon>)