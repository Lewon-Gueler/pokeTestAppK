package com.example.pokedexkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.detailview.*


class DetailActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailview)

        intent?.extras?.getInt("image")?.let {
            iVfull.setImageResource(it)
        }
        textViewN.text = intent.extras?.getString("name")
        textViewT.text = intent.extras?.getString("typ")
        textViewDn.text = "${intent.extras?.getInt("dex")}"


    }
}