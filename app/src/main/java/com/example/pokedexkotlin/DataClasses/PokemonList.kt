package com.example.pokedexkotlin.DataClasses

data class PokemonList (
    val count: Int? = null,
    val next: String? = null,
    val previos: String? = null,
    val results: List<Pokemon>)