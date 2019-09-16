package com.example.pokedexkotlin

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedexkotlin.networking.Api
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.detailview.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel
import com.facebook.imagepipeline.request.ImageRequestBuilder
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.common.ImageDecodeOptions
import kotlinx.android.synthetic.main.listcell.*


class DetailActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailview)


        //Getting the complete url from the first request (mainActivity)
        val newUrl = intent.extras?.getString("url")


        textViewN.text = intent.extras?.getString("name")

        //Getting the Image from Main Activity

        //val newImage = intent.extras?.getString("image")

        //val uri: Uri = intent.data

        val uriString: String? = intent?.extras?.getString("image")
        val uri: Uri = Uri.parse(uriString)


        iVfull.setImageURI(uri)


        //load image from cache

        /*val decodeOptions = ImageDecodeOptions.newBuilder().build()

        val request = ImageRequestBuilder
            .newBuilderWithSource(uri)
            .setImageDecodeOptions(decodeOptions)
            .setAutoRotateEnabled(true)
            .setLocalThumbnailPreviewsEnabled(true)
            .setLowestPermittedRequestLevel(RequestLevel.FULL_FETCH)
            .setProgressiveRenderingEnabled(false)
            .setResizeOptions(ResizeOptions(150, 150))
            .build()


         */
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
                textViewID.text = "${response?.body()?.id}"
                val specURL = response?.body()?.species?.speciesUrl


                service.getSpecies(specURL!!).enqueue(object : Callback<PokemonSpeciesData> {


                    override fun onResponse(call: Call<PokemonSpeciesData>?, response: Response<PokemonSpeciesData>?) {

                        textViewCR.text = response?.body()?.cr
                        textViewHC.text = "${response?.body()?.hc}"

                    }
                    override fun onFailure(call: Call<PokemonSpeciesData>?, t: Throwable?) {


                    }

                })
            }

            override fun onFailure(call: Call<PokemonData>?, t: Throwable?) {

            }

        })


    }
}