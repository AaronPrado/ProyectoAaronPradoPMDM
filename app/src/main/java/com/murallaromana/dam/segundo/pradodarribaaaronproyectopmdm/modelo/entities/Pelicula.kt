package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Pelicula(
    @SerializedName("title") var nombre: String,
    @SerializedName("genre") var genero: String,
    @SerializedName("directorFullname") var director: String,
    @SerializedName("rating") var nota: Double,
    @SerializedName("imageUrl") var url: String,
    @SerializedName("directorPhone") var telefono: String,
    @SerializedName("runtimeMinutes") var tiempo: Int,
) : Serializable