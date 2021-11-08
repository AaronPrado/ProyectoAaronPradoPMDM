package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.R
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.entities.Pelicula

class ListaPeliculasAdapter(val peliculas: List<Pelicula>) : RecyclerView.Adapter<ListaPeliculasAdapter.PeliculasHolder>() {

     class PeliculasHolder(view: View) : RecyclerView.ViewHolder(view) {
         val tvTitulo = itemView.findViewById<TextView>(R.id.tvTitulo)
         val tvGenero = itemView.findViewById<TextView>(R.id.tvGenero)
         val tvDirector = itemView.findViewById<TextView>(R.id.tvDirector)
         val tvValoracion = itemView.findViewById<TextView>(R.id.tvValoracion)
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculasHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pelicula, parent, false)

        return PeliculasHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: PeliculasHolder, position: Int) {
        val pelicula = peliculas.get(position)

        holder.tvTitulo.setText(pelicula.titulo)
        holder.tvGenero.setText(pelicula.genero)
        holder.tvDirector.setText(pelicula.director)
        holder.tvValoracion.setText(pelicula.valoracion)

    }

    override fun getItemCount() = peliculas.size
}