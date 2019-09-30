package com.example.pokedexkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexkotlin.Database.PListDatabase
import com.example.pokedexkotlin.Database.PokemonDatabase
import com.example.pokedexkotlin.networking.Api
import com.facebook.binaryresource.BinaryResource
import com.facebook.binaryresource.FileBinaryResource
import com.facebook.cache.common.CacheKey
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory
import com.facebook.imagepipeline.core.ImagePipelineFactory
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder
import io.realm.Realm
import io.realm.RealmConfiguration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CustomAdapter
    private val pokeList: MutableList<PokemonDatabase> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //using Fresco to show image
        Fresco.initialize(this)

        //Realm first Initial
        Realm.init(this)

        //Create Builder of Realm
        val config = RealmConfiguration.Builder()
            .name("personRealm")
            .inMemory() //Realm that runs entirely in memory without being persisted to disk.
            .build()

        val realm = Realm.getDefaultInstance()

        //How to get the id from Adapter ? Intent? 
       // val pokemon = realm.where(PokemonDatabase::class.java).equalTo("id",).findFirst()

        val recyclerView = findViewById<RecyclerView>(R.id.recyView)
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

        val db = realm.where(PokemonDatabase::class.java).findAll()

        if (db.isEmpty()) {
            Log.d("MainActivity Leer","ok")
            service.getAllRealm(100,0).enqueue(object : Callback<PListDatabase> {
                override fun onFailure(call: Call<PListDatabase>?, t: Throwable?) {

                }

                override fun onResponse(call: Call<PListDatabase>?, response: Response<PListDatabase>?) {
                    val resource = response?.body()

                    response?.body()?.results?.forEach {pokemon ->

                        val newURL =  pokemon.url
                        newURL?.let {
                            service.getPokeWithURLRealm(newURL).enqueue(object : Callback<PokemonDatabase> {
                                override fun onResponse(call: Call<PokemonDatabase>?, response: Response<PokemonDatabase>?) {

                                    val plistDatabase = PListDatabase()

                                    val allData = response?.body()
                                    realm.beginTransaction()

                                    realm.copyToRealmOrUpdate(allData)
                                    Log.d("current Pokemon", "${allData?.id} ${allData?.types?.get(0)?.type?.name}")

                                    realm.where(PokemonDatabase::class.java).findAll()
                                    realm.commitTransaction()

                                    allData?.let {
                                        pokeList.add(allData)
                                    }

                                    pokeList.sortBy {
                                        it.id
                                    }

                                    /*
                                    val realmPokemon = realm.createObject(PokemonDatabase::class.java, pokemon.id)
                                    val uri = realmPokemon.imageUri.toUri()

                                    //Fresco saving Image in files
                                    val request: ImageRequest = ImageRequestBuilder
                                        .newBuilderWithSource(uri)
                                        .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.DISK_CACHE)
                                        .setProgressiveRenderingEnabled(false)
                                        .build()

                                    val chacheKey: CacheKey = DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(request,this)
                                    val resource: BinaryResource = ImagePipelineFactory.getInstance().mainFileCache.getResource(chacheKey)
                                    val file = (resource as FileBinaryResource).file

                                    val imagePath = file.path

                                     */
                                    recyclerView.adapter = CustomAdapter(pokeList)

                                }

                                override fun onFailure(call: Call<PokemonDatabase>?, t: Throwable?) {

                                }

                            })
                        }

                    }

                }

            })
        }

        else { //If Datas are saved Load Data from Realm


            val dbp = realm.copyFromRealm(db)
            dbp.sortBy {
                it.id
            }
            Log.d("MainActivity Daten","${dbp.size}")
            recyclerView.adapter = CustomAdapter(dbp)


        }

    }

}

// Enum with Typs and Colors