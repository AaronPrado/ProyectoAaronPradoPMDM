package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm

import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.dao.retrofit.ApiRetrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://damapi.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiRetrofit = getRetrofit().create(ApiRetrofit::class.java)
}