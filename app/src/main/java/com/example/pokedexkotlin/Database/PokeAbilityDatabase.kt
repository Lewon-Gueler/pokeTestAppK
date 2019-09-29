package com.example.pokedexkotlin.Database

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class PokeAbilityDatabase (

    @SerializedName("ability")
    var ability: AbilityDatabase? = null,

    @SerializedName("slot")
    var slot: Int? = 0

) : RealmObject()