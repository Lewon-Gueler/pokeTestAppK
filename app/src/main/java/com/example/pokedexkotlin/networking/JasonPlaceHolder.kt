package com.example.pokedexkotlin.networking

import retrofit2.Call
import retrofit2.http.*

interface JasonPlaceHolder {

    @GET("get")
    fun getPosts(): Call<Post>

    @POST("post")
    fun sendPosts(@Body post: Post): Call<Post>

    @DELETE("delete")
    fun deletePost(): Call<Post>

}

data class Something(val string: String)