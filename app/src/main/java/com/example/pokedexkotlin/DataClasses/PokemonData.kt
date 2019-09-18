package com.example.pokedexkotlin.DataClasses

import com.google.gson.annotations.SerializedName
import retrofit2.http.Url

data class PokemonData (
     val height: Int?, val weight: Int?, val id: Int?, val species: PokemonSpecies?, val types: List<Types>?, val name: String?, val url: String, val sprites: Sprites?
)
