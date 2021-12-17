package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm

import android.app.Application
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.entities.Pelicula
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.dao.PeliculasDaoImpl

class App : Application() {
    companion object {
        var peliculas: ArrayList<Pelicula> = ArrayList()
    }

    override fun onCreate() {
        super.onCreate()

        val dao = PeliculasDaoImpl()
        peliculas= dao.getTodos()    }
}