package com.example.pokedexkotlin.networking

import com.google.gson.annotations.SerializedName

data class Post (
    val origin: String? = null,
    var url: String? = null)