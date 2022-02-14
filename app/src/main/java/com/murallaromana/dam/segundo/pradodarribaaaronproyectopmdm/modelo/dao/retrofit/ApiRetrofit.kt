package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.dao.retrofit


import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.entities.Pelicula
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.entities.Token
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.entities.Usuario
import retrofit2.Call
import retrofit2.http.*

interface ApiRetrofit {
    @POST("users/signup")
    fun signup(@Body users: Usuario): Call<Usuario>

    @POST("users/login")
    fun login(@Body user: Usuario): Call<Token>

    @GET("movies")
    fun getPeliculas(@Header("Authorization") token: String?): Call<List<Pelicula>>

    @GET("movies/{id}")
    fun getById(@Header("Authorization") token: String?, @Path("id") id: String): Call<Pelicula>

    @POST("movies")
    fun createPeliculas(@Header("Authorization") token: String?, @Body pelicula: Pelicula): Call<Unit>

    @DELETE("movies/{id}")
    fun borrarPeliculas(@Header("Authorization") token: String?, @Path("id") id: String?): Call<Unit>

    @PUT("movies")
    fun actualizarPeliculas(@Header("Authorization") token: String, @Body pelicula: Pelicula): Call<Unit>
}