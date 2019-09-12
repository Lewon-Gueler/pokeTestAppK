package com.example.pokedexkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedexkotlin.networking.Api
import com.example.pokedexkotlin.networking.PokemonData
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



        val recyclerView = recyView
        recyclerView.layoutManager = LinearLayoutManager(this)

        val arrPokemon: ArrayList<Pokemon> = ArrayList()
        arrPokemon.add(Pokemon("abra", R.drawable.abra, "psychic", 63))
        arrPokemon.add(Pokemon("bulbasaur", R.drawable.bulbasaur, "grass/poison", 1))
        arrPokemon.add(Pokemon("charmander", R.drawable.charmander, "fire", 4))
        arrPokemon.add(Pokemon("dratini", R.drawable.dratini, "dragon", 147))
        arrPokemon.add(Pokemon("eevee", R.drawable.eevee, "normal", 133))
        arrPokemon.add(Pokemon("heracross", R.drawable.heracross, "bug/fighting", 214))
        arrPokemon.add(Pokemon("larvitar", R.drawable.larvitar, "rock/ground", 246))
        arrPokemon.add(Pokemon("magikarp", R.drawable.magikarp, "water", 129))
        arrPokemon.add(Pokemon("pikachu", R.drawable.pikachu, "electric", 25))
        arrPokemon.add(Pokemon("squirtle", R.drawable.squirtle, "water", 7))

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

        service.getAll(20,0).enqueue(object : Callback<List<PokemonData>> {
            override fun onResponse(call: Call<List<PokemonData>>, response: Response<List<PokemonData>>) {
            }

            override fun onFailure(call: Call<List<PokemonData>>?, t: Throwable?) {

            }
        })
    }
}







        //val service = retrofit.create(JasonPlaceHolder::class.java)
        //val call = service.getPosts()


       /*button.setOnClickListener {
            service.sendPosts(PokemonData("dsalkfj", "fdjaksf")).enqueue(object : Callback<Answer> {
                override fun onResponse(call: Call<Answer>?, response: Response<PokemonData>?) {
                    if (response?.isSuccessful() == false) {
                        textView2.text = "Code ${response.code()}"
                        return
                    }

                    val stringBuilder = response?.let {
                        it.body().
                        it.body().origin + "" + "  " + it.body().url
                    }

                    textView2.text = stringBuilder
                }

                override fun onFailure(call: Call<PokemonData>?, t: Throwable?) {
                    textView2.text = t?.message
                }
            }) */
            /*var gson = Gson()
            var json: String = """{"id":1,"url":"login"}"""
            var testModel = gson.fromJson(json, Answer::class.java)
            textView2.text = "$testModel"
            //Assert.assertEquals(testModel.id, 1)
            //Assert.assertEquals(testModel.description, "Test")


        }  */







