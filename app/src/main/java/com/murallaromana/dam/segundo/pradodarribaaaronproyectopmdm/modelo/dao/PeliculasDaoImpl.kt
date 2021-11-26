package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.dao

import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.entities.Pelicula

class PeliculasDaoImpl : PeliculasDao {
    override fun getTodos() = listOf(
        Pelicula("Harry Potter y la piedra filosofal","Fantasía","Chris Columbus",7.9,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7xXJ15VEf7G9GdAuV1dO769yC73.jpg"),
        Pelicula("Origen","Ciencia ficción", "Christopher Nolan",8.3,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tXQvtRWfkUUnWJAn2tN3jERIUG.jpg"),
        Pelicula("Hamilton","Musical","Thomas Kail",8.4,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2/h1B7tW0t399VDjAcWJh8m87469b.jpg"),
    Pelicula("La milla verde","Drama","Frank Darabont",8.5,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1EFS40uFzv5ZVLSpu3xqYqnou67.jpg"),
    Pelicula("El club de la lucha","Drama","David Fincher",8.4,"https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1yWmCAIGSVNuTOGyz9F48W9g1Ux.jpg"))
}