package com.example.pokedexkotlin.DataClasses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ability (
    val name:String?, val url: String?, val is_hidden: Boolean?
): Parcelable