package com.example.pokedexkotlin.Fragments


import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokedexkotlin.R
import kotlinx.android.synthetic.main.detailview.*
import kotlinx.android.synthetic.main.fragment1.*

/**
 * A simple [Fragment] subclass.
 */
class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        textViewID.text = "ID: "
        textViewT.text = "height: "
        textViewDn.text= "weight: "
        textViewCR.text= "capture rate: "
        textViewHC.text = "hatch counter: "

    }
}
