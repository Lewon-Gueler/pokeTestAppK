package com.example.pokedexkotlin.DataClasses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonSpecies (
    @SerializedName ("name") val speciesName: String?,
    @SerializedName ("url") val speciesUrl: String?

) : Parcelable