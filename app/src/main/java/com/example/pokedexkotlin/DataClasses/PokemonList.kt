package com.example.pokedexkotlin.DataClasses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonList (
    val count: Int? = null,
    val next: String? = null,
    val previos: String? = null,
    val results: List<PokemonData>) : Parcelable