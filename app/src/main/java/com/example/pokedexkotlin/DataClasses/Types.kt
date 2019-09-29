package com.example.pokedexkotlin.DataClasses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Types (
    val type: Typ?, val slot: Int?
) : Parcelable

