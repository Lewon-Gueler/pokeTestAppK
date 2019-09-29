package com.example.pokedexkotlin.DataClasses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Typ (
    val name: String?
) : Parcelable

