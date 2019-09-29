package com.example.pokedexkotlin.DataClasses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokeAbility (
    val slot: Int?,val ability: Ability?
   ): Parcelable