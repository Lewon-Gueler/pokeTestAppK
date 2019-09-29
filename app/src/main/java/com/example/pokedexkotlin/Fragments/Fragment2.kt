package com.example.pokedexkotlin.Fragments


import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokedexkotlin.DataClasses.PokemonData
import com.example.pokedexkotlin.Database.PokemonDatabase
import com.example.pokedexkotlin.Database.SpritesDatabase
import com.example.pokedexkotlin.R
import com.facebook.drawee.view.SimpleDraweeView
import io.realm.Realm


/**
 * A simple [Fragment] subclass.
 */
class Fragment2 : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance2(pokemon: PokemonDatabase?) = Fragment2().apply {
            arguments = Bundle().apply {
                pokemon?.id.let {
                    it?.let { it1 -> putInt("id2", it1) }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val imageView = view.findViewById<SimpleDraweeView>(R.id.iVShinyBack)
        val imageView2 = view.findViewById<SimpleDraweeView>(R.id.iVShinyFront)

        val imageView3 = view.findViewById<SimpleDraweeView>(R.id.iVShinyWFront)
        val imageView4 = view.findViewById<SimpleDraweeView>(R.id.iVShinyWBack)


        arguments?.getInt("id2").let {
            //Create Builder of Realm

            val realm = Realm.getDefaultInstance()

            val pokemon = realm.where(PokemonDatabase::class.java).equalTo("id", it).findFirst()

            val shinyFront = pokemon?.sprites?.front_shiny
            val shinyBack = pokemon?.sprites?.back_shiny

            val shinyWFront = pokemon?.sprites?.front_shiny_female
            val shinyWBack = pokemon?.sprites?.back_shiny_female

            val uriFront = Uri.parse(shinyFront)
            imageView.setImageURI(uriFront)

            val uriBack = Uri.parse(shinyBack)
            imageView2.setImageURI(uriBack)

            if (shinyWFront !=null) {
                val uriW = Uri.parse(shinyWFront)
                imageView3.setImageURI(uriW)
            }

            if (shinyWBack !=null) {
            val uriWBack = Uri.parse(shinyWBack)
               imageView4.setImageURI(uriWBack)

            }

        }
    }
}
