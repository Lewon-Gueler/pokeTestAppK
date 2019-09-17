package com.example.pokedexkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedexkotlin.DataClasses.PokemonImages
import com.example.pokedexkotlin.networking.Api
import com.example.pokedexkotlin.DataClasses.PokemonList
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.listcell.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient



class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //using Fresco to show image
        Fresco.initialize(this)


        val recyclerView = recyView
        recyclerView.layoutManager = LinearLayoutManager(this)


        //recyclerView.adapter = CustomAdapter() //ArrayList vom Adapter übergeben

        //Logging
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        //creating Retrofit
        var retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

        val service = retrofit.create(Api::class.java)

        //GET Names and URL of 300 Pokemon
        service.getAll(300,0).enqueue(object : Callback<PokemonList> {
            override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
                if (!response.isSuccessful) {
                    tvName.text = "Code ${response.code()}"
                    return
                }
                recyclerView.adapter = CustomAdapter(response.body().results)

            }
            override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                tvName.text = t.message
            }
        })

        //creating Retrofit
        var retrofit2 = Retrofit.Builder()
            .baseUrl("https://pokeres.bastionbot.org/images/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

        val service2 = retrofit2.create(Api::class.java)


        //Need id from each Pokemon

        //GET Images
        service2.getImages(1).enqueue(object : Callback<PokemonImages> {
            override fun onResponse(call: Call<PokemonImages>?, response: Response<PokemonImages>?) {

            }

            override fun onFailure(call: Call<PokemonImages>?, t: Throwable?) {

            }


        })

    }
}

// Enum with Typs and Colors







