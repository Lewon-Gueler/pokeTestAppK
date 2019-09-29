package com.example.pokedexkotlin.Fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokedexkotlin.DataClasses.PokemonData
import com.example.pokedexkotlin.Database.PokemonDatabase
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.fragment1.*



/**
 * A simple [Fragment] subclass.
 */
class Fragment1 : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance(pokemon: PokemonDatabase?) = Fragment1().apply {
            arguments = Bundle().apply {
                pokemon?.id?.let { putInt("id1", it) }
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(com.example.pokedexkotlin.R.layout.fragment1, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

       arguments?.getInt("id1").let {

           //Create Builder of Realm

           val realm = Realm.getDefaultInstance()

           //Direkt die TypenDatabase ansprechen ?
           val pokemon = realm.where(PokemonDatabase::class.java).equalTo("id", it).findFirst()
           textViewID2.text = "${pokemon?.id}"
           textViewT2.text = "${pokemon?.height}"
           textViewDn2.text = "${pokemon?.weight}"
           textViewCR2.text = "${pokemon?.name}"
           textViewHC2.text = "${pokemon?.order}"
       }

        textViewID.text = "ID: "
        textViewT.text = "Height: "
        textViewDn.text= "Weight: "
        textViewCR.text= "Name: "
        textViewHC.text = "Order: "

        }




}
