package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.dao

import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.entities.Pelicula

interface PeliculasDao {
    fun getTodos(): ArrayList<Pelicula>
}