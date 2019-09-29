package com.example.pokedexkotlin.Database

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class SpritesDatabase (

    @SerializedName("back_shiny")
    var back_shiny:String? = null,

    @SerializedName("back_shiny_female")
    var back_shiny_female:String? = null,

    @SerializedName("front_shiny")
    var front_shiny:String? = null,

    @SerializedName("front_shiny_female")
    var front_shiny_female: String? = null

) : RealmObject()