package com.example.pokedexkotlin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.example.pokedexkotlin.DataClasses.PokemonData
import com.example.pokedexkotlin.Database.PokemonDatabase


class CustomAdapter(val pokemon: MutableList<PokemonDatabase>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.listcell, parent, false)
        return ViewHolder(v)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return pokemon.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon: PokemonDatabase = pokemon[position]
        holder.titel.text = pokemon.name
        val uri = Uri.parse("https://pokeres.bastionbot.org/images/pokemon/${position+1}.png")
        holder.imageView.setImageURI(uri)


        //val pokeTypName = pokemon.types?.get(0)?.type?.name

        if (pokemon.types?.size == 2 ) {
            holder.typ2.text = pokemon.types?.get(0)?.type?.name
            holder.typ1.text = pokemon.types?.get(1)?.type?.name
        } else {
            holder.typ2.text = pokemon.types?.get(0)?.type?.name
            holder.typ1.visibility = View.INVISIBLE

            //holder.typ2.resources.getColor()

        }


        holder.itemView.setOnClickListener {

            val intent = Intent (holder.titel.context, DetailActivity::class.java).apply {

                this.putExtra("id",pokemon.id)
                this.putExtra("image", uri.toString())

            }

            holder.titel.context.startActivity(intent)

        }

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titel = itemView.findViewById<TextView>(R.id.tvName)
        var imageView = itemView.findViewById<SimpleDraweeView>(R.id.iVShinyFront)
        var typ1 = itemView.findViewById<TextView>(R.id.typ1)
        var typ2 = itemView.findViewById<TextView>(R.id.typ2)

    }

    /*fun TypColor(pokeTypName:String) {
        when (pokeTypName) {
            "fire" -> holder.typ2.setBackgroundColor(Color.RED)
            else -> holder.typ2.setBackgroundColor(Color.GRAY)
        }

    } */
}