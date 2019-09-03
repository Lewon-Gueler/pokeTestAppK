package com.example.pokedexkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedexkotlin.networking.JasonPlaceHolder
import com.example.pokedexkotlin.networking.Post
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = btn_Post
        val textView2 = txtPost
        val textView = txtResponse
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

        recyclerView.adapter = CustomAdapter(arrPokemon)




        var retrofit = Retrofit.Builder()
            .baseUrl("http://httpbin.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(JasonPlaceHolder::class.java)
        //val call = service.getPosts()
        val call2 = service.sendPosts(Post("dsalkfj", "fdjaksf"))


        button.setOnClickListener {
            call2.enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>?, response: Response<Post>?) {
                    if (response?.isSuccessful() == false) {
                        textView.text = "Code ${response.code()}"
                        return
                    }

                    val stringBuilder = response?.let {
                        it.body().origin + "" +  it.body().url
                    }

                    textView.text = stringBuilder
                }

                override fun onFailure(call: Call<Post>?, t: Throwable?) {
                    textView.text = t?.message
                }
            })
        }


    }
}

//To-Do Post, put, delete Request


