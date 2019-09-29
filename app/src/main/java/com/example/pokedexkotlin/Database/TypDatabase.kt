package com.example.pokedexkotlin.Database

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class TypDatabase: RealmObject() {
    @SerializedName("name")
    var name: String? = null
}



