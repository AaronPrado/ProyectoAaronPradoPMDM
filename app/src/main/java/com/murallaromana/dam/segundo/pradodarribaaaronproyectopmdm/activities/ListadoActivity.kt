package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.R
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.RetrofitClient
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.adapters.ListaPeliculasAdapter
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.databinding.ActivityListadoBinding
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.dao.mockImpl.PeliculasDaoImpl
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.entities.Pelicula
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListadoBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Lista de Peliculas"
    }

    override fun onResume() {
        super.onResume()

        sharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
        val token = sharedPreferences.getString("token", "No encontrado")

        val llamadaApi: Call<List<Pelicula>> = RetrofitClient.apiRetrofit.getPeliculas("Bearer $token")
        llamadaApi.enqueue(object : Callback<List<Pelicula>> {
            override fun onResponse(call: Call<List<Pelicula>>, response: Response<List<Pelicula>>) {
                if (response.code() > 299 || response.code() < 200) {
                    Toast.makeText(
                        this@ListadoActivity,
                        "No se pudo cargar la lista",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val listaPeliculas = response.body()!!
                    val layoutManager = LinearLayoutManager(this@ListadoActivity)
                    val adapter = ListaPeliculasAdapter(listaPeliculas)
                    binding.rvPeliculasList.adapter = adapter
                    binding.rvPeliculasList.layoutManager = layoutManager
                }
            }

            override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                Log.d("response: failure", t.message.toString())
            }
        })


        binding.btAdd.setOnClickListener() {
            val intent = Intent(this, DetallesActivity::class.java)
            startActivity(intent)
        }
    }

}