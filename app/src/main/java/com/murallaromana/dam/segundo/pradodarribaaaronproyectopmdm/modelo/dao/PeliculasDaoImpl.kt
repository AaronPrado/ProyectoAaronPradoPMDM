package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.dao

import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.entities.Pelicula

class PeliculasDaoImpl : PeliculasDao {
    override fun getTodos() = listOf(
        Pelicula("Expediente Warren","Terror","James Wan",0,1),
        Pelicula("Expediente Warren 2","Terror","James Wan",1,1),
        Pelicula("Expediente Warren 3","Terror","James Wan",2,1)
    )
}