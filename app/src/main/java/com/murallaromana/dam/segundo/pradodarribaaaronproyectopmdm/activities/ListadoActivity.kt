package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.R
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.adapters.ListaPeliculasAdapter
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.databinding.ActivityListadoBinding
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.dao.PeliculasDaoImpl

class ListadoActivity  : AppCompatActivity() {

    private lateinit var binding: ActivityListadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val peliculasDao = PeliculasDaoImpl()
        val listaPeliculas = peliculasDao.getTodos()

        val layoutManager = LinearLayoutManager(this)
        val adapter = ListaPeliculasAdapter(listaPeliculas)

        binding.rvPeliculasList.adapter = adapter
        binding.rvPeliculasList.layoutManager = layoutManager
    }
}