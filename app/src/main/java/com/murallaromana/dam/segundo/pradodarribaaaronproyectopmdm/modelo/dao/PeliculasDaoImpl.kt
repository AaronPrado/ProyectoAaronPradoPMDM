package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.dao

import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.entities.Pelicula

class PeliculasDaoImpl : PeliculasDao {
    override fun getTodos() = listOf(
        Pelicula(1,"Expediente Warren","Terror","James Wan",0.6),
        Pelicula(2,"Expediente Warren 2","Terror","James Wan",1.0),
        Pelicula(4,"Expediente Warren 3","Terror","James Wan",2.2)
    )
}