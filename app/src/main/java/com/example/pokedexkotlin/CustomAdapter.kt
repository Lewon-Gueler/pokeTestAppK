package com.example.pokedexkotlin

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.example.pokedexkotlin.DataClasses.PokemonData


class CustomAdapter(val pokemon: List<PokemonData>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


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
        val uri = Uri.parse("https://pokeres.bastionbot.org/images/pokemon/${position+1}.png")
        holder.imageView.setImageURI(uri)
        holder.typ1.text = pokemon.types?.first()?.type?.name
        holder.typ2.text = pokemon.types?.get(0)?.type?.name //Steht drin aber crash bei 1
       // holder.typ1.text = if (pokemon.types.first().type.name == "FIRE") TypePokemon.FIRE.colorType


/*
        val request = ImageRequestBuilder.newBuilderWithSource(uri).setCacheChoice(ImageRequest.CacheChoice.SMALL)

        //Checking if something is in chache
        val imagePipeline = Fresco.getImagePipeline()
        val inDiskChache = imagePipeline.isInDiskCache(uri)

        val inDiskChacheSync = imagePipeline.isInDiskCacheSync(uri)
        Toast.makeText(holder.titel.context,"$inDiskChacheSync",Toast.LENGTH_SHORT).show()

*/

        holder.itemView.setOnClickListener {

            //val pokeIndex = pokemon.url
            //["https:" ,"","pokeapi.co","api","v2","version-group","6" ,""]
            // val pokemonId = pokeIndexParts[pokeIndexParts.size-2] //Zweit letzte Stelle von der url

            val intent = Intent (holder.titel.context, DetailActivity::class.java)
            intent.putExtra("name", holder.titel.text as String)
            intent.putExtra("url", pokemon.url )
            intent.putExtra("image", uri.toString())
            holder.titel.context.startActivity(intent)

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titel = itemView.findViewById<TextView>(R.id.tvName)
        var imageView = itemView.findViewById<SimpleDraweeView>(R.id.myImageView)
        var typ1 = itemView.findViewById<TextView>(R.id.typ1)
        var typ2 = itemView.findViewById<TextView>(R.id.typ2)


    }

}