package com.example.pokedexkotlin.DataClasses

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import retrofit2.http.Url

//@Parcelize
data class PokemonData (
     val height: Int?, val weight: Int?, val id: Int?, val species: PokemonSpecies?, val types: List<Types>?, val name: String?, val url: String, val sprites: Sprites?, val abilities: List<Ability>?
)

