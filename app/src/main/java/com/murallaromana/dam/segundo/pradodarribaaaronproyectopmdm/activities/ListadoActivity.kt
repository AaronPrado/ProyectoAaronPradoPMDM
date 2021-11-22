package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.R
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.adapters.ListaPeliculasAdapter
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.databinding.ActivityListadoBinding
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.dao.PeliculasDaoImpl

class ListadoActivity  : AppCompatActivity() {

    private lateinit var binding: ActivityListadoBinding
    private lateinit var btAdd: FloatingActionButton

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

        val dividerItemDecoration = DividerItemDecoration(
            binding.rvPeliculasList.context,
            layoutManager.orientation
        )
        binding.rvPeliculasList.addItemDecoration(dividerItemDecoration)

        btAdd = findViewById(R.id.btAdd)
        btAdd.setOnClickListener() {
            val intent = Intent(this, DetallesActivity::class.java)
            startActivity(intent)
        }
    }
}