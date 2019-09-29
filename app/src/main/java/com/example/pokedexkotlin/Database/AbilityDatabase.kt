package com.example.pokedexkotlin.Database

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class AbilityDatabase: RealmObject() {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("url")
    var url: String? = null
}
