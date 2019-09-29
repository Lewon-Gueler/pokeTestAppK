package com.example.pokedexkotlin

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedexkotlin.DataClasses.*
import com.example.pokedexkotlin.Database.PokemonDatabase
import com.example.pokedexkotlin.Fragments.Fragment1
import com.example.pokedexkotlin.Fragments.Fragment2
import com.example.pokedexkotlin.Fragments.Fragment3
import com.example.pokedexkotlin.networking.Api
import com.facebook.drawee.backends.pipeline.Fresco
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.detailview.*






class DetailActivity : AppCompatActivity(){


lateinit var frag1: Fragment1
lateinit var frag2: Fragment2
lateinit var frag3: Fragment3


override fun onCreate(savedInstanceState: Bundle? ) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.detailview)

    Fresco.initialize(this)

    //Setting Image
    val uriString: String? = intent?.extras?.getString("image")
    val uri: Uri = Uri.parse(uriString)
    iVfull.setImageURI(uri)

    //Daten die ich brauche von der Datenbank aufrufen anhand der ID bzw, Primärschlssels
    val pokemonId = intent.getIntExtra("id",0)


    val realm = Realm.getDefaultInstance()

    try {
        val pokemon = realm.where(PokemonDatabase::class.java).equalTo("id", pokemonId).findFirst()
        val newName = pokemon?.name


        val newtyp1 = pokemon?.types?.get(0)?.type?.name
        val pokemonOrder = pokemon?.order

        //Setting Name
        textViewN.text = newName

        //Setting Types
        if (pokemon?.types?.size == 2 ) {
            typ2.text = newtyp1
            typ1.text = pokemon.types.get(1)?.type?.name
        } else {
            typ2.text = newtyp1
            typ1.visibility = View.INVISIBLE
        }

        val id = pokemon?.id
        frag1 = Fragment1.newInstance(pokemon)
        frag2 = Fragment2.newInstance2(pokemon)
        frag3 = Fragment3.newInstance3(pokemon)


        // Fragment Stuff
        val adapter = MyViewPagerAdapter(supportFragmentManager)

        adapter.addFragmentTitle(frag1, "About")
        adapter.addFragmentTitle(frag2, "Shinys")
        adapter.addFragmentTitle(frag3, "Ability")

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        ibBack.setOnClickListener {
            super.onBackPressed()
        }

        ibFavo.setOnClickListener {
            ibFavo.setBackgroundResource( R.drawable.ic_favo)
        }

    } finally {
        realm.close()
    }

    //tabs.tabTextColors

    /*
    //Get List from
   val bundle = intent?.extras?.getBundle("Bundle")
   val pokemon = bundle?.getParcelable<PokemonData>("poke")

    val newUrl = pokemon?.url
    val newName = pokemon?.name
    val newtyp1 = pokemon?.types?.get(0)?.type?.name

    //Setting Name
    textViewN.text = newName

    //Setting Types
    if (pokemon?.types?.size == 2 ) {
       typ2.text = newtyp1
       typ1.text = pokemon.types.get(1).type?.name
    } else {
        typ2.text = newtyp1
        typ1.visibility = View.INVISIBLE

    }
*/

/*
    frag1 = Fragment1.newInstance(pokemon)
    frag2 = Fragment2.newInstance2(pokemon)
    frag3 = Fragment3.newInstance3(pokemon)


    // Fragment Stuff
    val adapter = MyViewPagerAdapter(supportFragmentManager)

    adapter.addFragmentTitle(frag1, "About")
    adapter.addFragmentTitle(frag2, "Shinys")
    adapter.addFragmentTitle(frag3, "Ability")

    viewPager.adapter = adapter
    tabs.setupWithViewPager(viewPager)

    ibBack.setOnClickListener {
        super.onBackPressed()
    }

    ibFavo.setOnClickListener {
        ibFavo.setBackgroundResource( R.drawable.ic_favo)
    }


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
           //Evtl. Response for more Infromation e.g. efect_explanation
        }

        override fun onFailure(call: Call<PokemonData>?, t: Throwable?) {

        }
    })

 */

}
}

