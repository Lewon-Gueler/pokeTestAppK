package com.example.pokedexkotlin

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView




class CustomAdapter(val pokemon: List<Pokemon>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.listcell, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return pokemon.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon: Pokemon = pokemon[position]
        holder.titel.text = pokemon.name
        val uri = Uri.parse("https://pokeres.bastionbot.org/images/pokemon/${position+1}.png")
        holder.imageView.setImageURI(uri)

/*
        val request = ImageRequestBuilder.newBuilderWithSource(uri).setCacheChoice(ImageRequest.CacheChoice.SMALL)

        //Checking if something is in chache
        val imagePipeline = Fresco.getImagePipeline()
        val inDiskChache = imagePipeline.isInDiskCache(uri)

        val inDiskChacheSync = imagePipeline.isInDiskCacheSync(uri)
        Toast.makeText(holder.titel.context,"$inDiskChacheSync",Toast.LENGTH_SHORT).show()

*/

        holder.itemView.setOnClickListener {

            val pokeIndex = pokemon.url

            //["https:" ,"","pokeapi.co","api","v2","version-group","6" ,""]
            val pokeIndexParts = pokemon.url.split("/")
            val pokemonId = pokeIndexParts[pokeIndexParts.size-2] //Zweit letzte Stelle von der url

            //Toast.makeText(holder.titel.context, pokemonId , Toast.LENGTH_LONG).show()

           val intent = Intent (holder.titel.context, DetailActivity::class.java)
            intent.putExtra("name", holder.titel.text as String)
            intent.putExtra("url", pokeIndex )

            //intent.setData(uri)
            intent.putExtra("image", uri.toString())

            //Gives the imageID
            //intent.putExtra("imageID", pokemonId)

            holder.titel.context.startActivity(intent)


            /*
           intent.putExtra("image", pokemon.image )
           intent.putExtra("typ", pokemon.typ)
           intent.putExtra("dex", pokemon.dex)
           */



        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titel = itemView.findViewById<TextView>(R.id.tvName)
        var imageView = itemView.findViewById<SimpleDraweeView>(R.id.myImageView)

    }

}