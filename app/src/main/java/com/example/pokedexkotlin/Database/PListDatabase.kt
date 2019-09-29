package com.example.pokedexkotlin.Database

import com.google.gson.annotations.SerializedName
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class PListDatabase: RealmObject() {
    @PrimaryKey
    @SerializedName("id")
    var id: Int? = 0

    @SerializedName("results")
    var results: RealmList<PokemonDatabase> = RealmList()
}