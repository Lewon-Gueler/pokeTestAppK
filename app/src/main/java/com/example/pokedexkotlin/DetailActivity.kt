package com.example.pokedexkotlin

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedexkotlin.DataClasses.PokemonData
import com.example.pokedexkotlin.DataClasses.PokemonSpeciesData
import com.example.pokedexkotlin.Fragments.Fragment1
import com.example.pokedexkotlin.Fragments.Fragment2
import com.example.pokedexkotlin.Fragments.Fragment3
import com.example.pokedexkotlin.Fragments.Fragment4
import com.example.pokedexkotlin.networking.Api
import kotlinx.android.synthetic.main.detailview.*
import kotlinx.android.synthetic.main.fragment1.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DetailActivity : AppCompatActivity(){

    lateinit var frag1: Fragment1
    lateinit var frag2: Fragment2
    lateinit var frag3: Fragment3
    lateinit var frag4: Fragment4

    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailview)

        val newUrl = intent.extras?.getString("url")
        val newName = intent.extras.getString("name")

        //Setting Name
        textViewN.text = newName

        //Setting Image
        val uriString: String? = intent?.extras?.getString("image")
        val uri: Uri = Uri.parse(uriString)
        iVfull.setImageURI(uri)

        frag1 = Fragment1()
        frag2 = Fragment2()
        frag3 = Fragment3()
        frag4 = Fragment4()

        // Fragment Stuff
        val adapter = MyViewPagerAdapter(supportFragmentManager)

        adapter.addFragmentTitle(frag1, "About")
        adapter.addFragmentTitle(Fragment2(), "Sprites")
        adapter.addFragmentTitle(Fragment3(), "Moves")
        adapter.addFragmentTitle(Fragment4(), "Evolution")


        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)


        //Sending Name to Fragment
        val data = Bundle()
        val fragment = Fragment1()
        data.putString("name", newName as String)
        fragment.arguments = data

        ibBack.setOnClickListener {
            super.onBackPressed()
        }


        /* //Getting the complete url from the first request (mainActivity)
         textViewN.text = intent.extras?.getString("name")
         Getting the Image from Main Activity
         val newImage = intent.extras?.getString("image")
         val uri: Uri = intent.data
         val uriString: String? = intent?.extras?.getString("image")
         val uri: Uri = Uri.parse(uriString)
         iVfull.setImageURI(uri)

         //load image from cache
         val decodeOptions = ImageDecodeOptions.newBuilder().build()
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
                frag1.textViewID2.text = "${response?.body()?.id}"
                frag1.textViewT2.text = "${response?.body()?.height}"
                frag1.textViewDn2.text ="${response?.body()?.weight}"


                val specURL = response?.body()?.species?.speciesUrl


                service.getSpecies(specURL!!).enqueue(object : Callback<PokemonSpeciesData> {

                    override fun onResponse(call: Call<PokemonSpeciesData>?, response: Response<PokemonSpeciesData>?) {
                        frag1.textViewCR2.text = response?.body()?.cr
                        frag1.textViewHC2.text = "${response?.body()?.hc}"
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
// Todo Lat init für die ganzen textViews