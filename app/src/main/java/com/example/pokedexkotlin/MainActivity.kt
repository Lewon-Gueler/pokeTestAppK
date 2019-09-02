package com.example.pokedexkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = recyView
        recyclerView.layoutManager = LinearLayoutManager(this)

        val arrPokemon: ArrayList<Pokemon> = ArrayList()
        arrPokemon.add(Pokemon("abra", R.drawable.abra,"psychic",63 ))
        arrPokemon.add(Pokemon("bulbasaur", R.drawable.bulbasaur, "grass/poison", 1))
        arrPokemon.add(Pokemon("charmander", R.drawable.charmander,"fire", 4))
        arrPokemon.add(Pokemon("dratini", R.drawable.dratini, "dragon", 147 ))
        arrPokemon.add(Pokemon("eevee", R.drawable.eevee,  "normal", 133))
        arrPokemon.add(Pokemon("heracross", R.drawable.heracross, "bug/fighting", 214 ))
        arrPokemon.add(Pokemon("larvitar", R.drawable.larvitar,  "rock/ground", 246))
        arrPokemon.add(Pokemon("magikarp", R.drawable.magikarp, "water", 129))
        arrPokemon.add(Pokemon("pikachu", R.drawable.pikachu,  "electric", 25))
        arrPokemon.add(Pokemon("squirtle", R.drawable.squirtle, "water", 7))

        recyclerView.adapter = CustomAdapter(arrPokemon)



        /*recyclerView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent (this, DetailActivity::class.java).apply {//apply better version instead of intent.putExtra...
                putExtra("name", arrPokemon[position].name)
                putExtra("image", arrPokemon[position].image)
                putExtra("typ", arrPokemon[position].typ)
                putExtra("dex", arrPokemon[position].dex)
            }

            startActivity(intent)
        } */

        }
    }

