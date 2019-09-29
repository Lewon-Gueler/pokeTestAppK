package com.example.pokedexkotlin.Database


import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TypesDatabase (

    @SerializedName("type")
    var type: TypDatabase? = null,

    @SerializedName("slot")
    var slot: Int? = 0

) : RealmObject()




