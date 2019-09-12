package com.example.pokedexkotlin

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexkotlin.networking.PokemonData

class CustomAdapter(val pokemon: ArrayList<PokemonData>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.listcell, parent, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return pokemon.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon: PokemonData = pokemon[position]
        holder.titel.text = pokemon.name

        //holder.image.setImageResource(pokemon.image)

        holder.itemView.setOnClickListener {

           /* val intent = Intent (holder.titel.context, DetailActivity::class.java)
            intent.putExtra("name", holder.titel.text as String)
            intent.putExtra("image", pokemon.image )
            intent.putExtra("typ", pokemon.typ)
            intent.putExtra("dex", pokemon.dex)

            holder.titel.context.startActivity(intent)
            */

            Toast.makeText(holder.titel.context, "open new Screen" , Toast.LENGTH_SHORT).show()

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titel = itemView.findViewById<TextView>(R.id.tvName)
        var image = itemView.findViewById<ImageView>(R.id.ivPokemon)
//        var typ = itemView.findViewById<TextView>(R.id.textViewT)
//        var dex = itemView.findViewById<TextView>(R.id.textViewDn)
    }

}