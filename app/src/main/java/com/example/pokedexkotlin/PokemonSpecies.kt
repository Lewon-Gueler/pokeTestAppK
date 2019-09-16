package com.example.pokedexkotlin

import com.google.gson.annotations.SerializedName

data class PokemonSpecies (
    @SerializedName ("name") val speciesName: String,
    @SerializedName ("url") val speciesUrl: String

)