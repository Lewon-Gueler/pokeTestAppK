package com.example.pokedexkotlin.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pokedexkotlin.DataClasses.PokemonData
import com.example.pokedexkotlin.Database.PokemonDatabase
import com.example.pokedexkotlin.R
import com.facebook.drawee.view.SimpleDraweeView
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment1.*

/**
 * A simple [Fragment] subclass.
 */
class Fragment3 : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance3(pokemon: PokemonDatabase?) = Fragment3().apply {
            arguments = Bundle().apply {
                pokemon?.id.let {
                    it?.let { it1 -> putInt("id3", it1) }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val txtView = view.findViewById<TextView>(R.id.txtAbi)
        val txtView2 = view.findViewById<TextView>(R.id.txtAbility)

        val txtView3 = view.findViewById<TextView>(R.id.txtAbi2)
        val txtView4 = view.findViewById<TextView>(R.id.txtAbility2)


        arguments?.getInt("id3").let {

            val realm = Realm.getDefaultInstance()
            val pokemon = realm.where(PokemonDatabase::class.java).equalTo("id", it).findFirst()

            val abilityName = pokemon?.abilities?.get(0)?.ability?.name
            txtView2.text = "$abilityName"
            Log.d("current","${pokemon?.abilities?.get(0)?.ability?.name} ${pokemon?.abilities?.get(0)?.ability}")

            if (pokemon?.abilities?.size!! > 1 ) {
                txtView4.text = "${pokemon?.abilities?.get(1)?.ability?.name}"
            }
            else {
                txtView4.text = "No second Ability"
            }

        }



/*
        arguments?.getParcelable<PokemonData>("data3").let {
            val abilityName = it?.abilities?.get(0)?.ability?.name
            txtView2.text = "$abilityName"


            if (it?.abilities?.size!! > 1 ) {
                txtView4.text = "${it?.abilities?.get(1)?.ability?.name}"
            }
            else {
                txtView4.text = "No second Ability"
            }

        } */
    }
}
