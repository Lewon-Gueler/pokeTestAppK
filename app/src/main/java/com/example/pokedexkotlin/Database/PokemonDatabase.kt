package com.example.pokedexkotlin.Database

import com.google.gson.annotations.SerializedName
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass


open class PokemonDatabase (

    @PrimaryKey
    @SerializedName("id")
    var id: Int? = 0,

    @SerializedName("height")
    var height: Int? = 0,

    @SerializedName("weight")
    var weight: Int? = 0,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("order")
    var order: Int? = 0,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("types")
    var types: RealmList<TypesDatabase> = RealmList(),

    @SerializedName("sprites")
    var sprites: SpritesDatabase? = null,

    @SerializedName("abilities")
    var abilities: RealmList<PokeAbilityDatabase> = RealmList(),

    @SerializedName("imageurl")
    var imageUri: String = "https://pokeres.bastionbot.org/images/pokemon/${id}.png"

):RealmObject()
