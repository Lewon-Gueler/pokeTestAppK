package com.example.pokedexkotlin.DataClasses

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import retrofit2.http.Url


data class PokemonData (
     val height: Int?, val weight: Int?, val id: Int?, val species: PokemonSpecies?, val types: List<Types>?, val name: String?, val url: String, val sprites: Sprites?, val abilities: List<Ability>?
) : Parcelable {
     constructor(parcel: Parcel) : this(
          parcel.readValue(Int::class.java.classLoader) as? Int,
          parcel.readValue(Int::class.java.classLoader) as? Int,
          parcel.readValue(Int::class.java.classLoader) as? Int,
          TODO("species"),
          TODO("types"),
          parcel.readString(),
          parcel.readString(),
          parcel.readParcelable(Sprites::class.java.classLoader),
          TODO("abilities")
     ) {
     }

     override fun writeToParcel(parcel: Parcel, flags: Int) {
          parcel.writeValue(height)
          parcel.writeValue(weight)
          parcel.writeValue(id)
          parcel.writeString(name)
          parcel.writeString(url)
          parcel.writeParcelable(sprites, flags)
     }

     override fun describeContents(): Int {
          return 0
     }

     companion object CREATOR : Parcelable.Creator<PokemonData> {
          override fun createFromParcel(parcel: Parcel): PokemonData {
               return PokemonData(parcel)
          }

          override fun newArray(size: Int): Array<PokemonData?> {
               return arrayOfNulls(size)
          }
     }
}

