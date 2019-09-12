package com.example.pokedexkotlin.networking

import com.google.gson.annotations.SerializedName

data class PokemonData (
    val number: Int? = null,
    val name: String? = null,
    val url: String? = null,
    val results: ArrayList<PokemonData>)