package com.example.pokedexkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class CustomAdapter(var context: Context, var pokemon: ArrayList<Pokemon>): BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
      val view: View?
        if ( convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listcell, parent, false)
        } else {
            view = convertView
        }
        view?.findViewById<TextView>(R.id.tvName)?.text = pokemon[position].name
        view?.findViewById<ImageView>(R.id.ivPokemon)?.setImageResource(pokemon[position].image)

        return view as View
    }

    override fun getItem(position: Int): Any {
        return pokemon[position] //
    }

    override fun getItemId(position: Int): Long {
        return position.toLong() //
    }

    override fun getCount(): Int {
        return pokemon.count() //
    }
}