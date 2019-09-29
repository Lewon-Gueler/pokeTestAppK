package com.example.pokedexkotlin.DataClasses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonSpeciesData (
    @SerializedName("capture_rate") val cr : String?,
    @SerializedName("hatch_counter") val hc: String?
) : Parcelable