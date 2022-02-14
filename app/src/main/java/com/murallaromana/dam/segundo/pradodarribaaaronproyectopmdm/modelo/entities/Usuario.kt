package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.entities

import com.google.gson.annotations.SerializedName

class Usuario(
    var email: String,
    @SerializedName("password") var password: String
)
