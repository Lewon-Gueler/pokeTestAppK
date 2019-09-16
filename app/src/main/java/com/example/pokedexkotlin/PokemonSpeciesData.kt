package com.example.pokedexkotlin

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesData (
    @SerializedName("capture_rate") val cr : String,
    @SerializedName("hatch_counter") val hc: String
)