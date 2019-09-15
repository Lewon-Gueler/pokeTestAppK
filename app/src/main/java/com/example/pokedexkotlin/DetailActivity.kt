package com.example.pokedexkotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedexkotlin.networking.Api
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.detailview.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DetailActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailview)


        //Getting the complete url from the first request (mainActivity)
        val newUrl = intent.extras?.getString("url")


        textViewN.text = intent.extras?.getString("name")

        //Toast.makeText(this, newUrl , Toast.LENGTH_LONG).show()


        //Logging
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        //creating Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/") //why not url variable here ?
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

        val service = retrofit.create(Api::class.java)

        service.getPokeWithURL(newUrl!!).enqueue(object : Callback<PokemonData> {

            override fun onResponse(call: Call<PokemonData>?, response: Response<PokemonData>?) {
                textViewT.text = "${response?.body()?.height}"
                textViewDn.text ="${response?.body()?.weight}"
            }

            override fun onFailure(call: Call<PokemonData>?, t: Throwable?) {

            }

        })


        /* textViewT.text = intent.extras?.getString("typ")
        intent?.extras?.getInt("image")?.let {
            iVfull.setImageResource(it)
        }
        textViewDn.text = "${intent.extras?.getInt("dex")}"
        */

    }
}