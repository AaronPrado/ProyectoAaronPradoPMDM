package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.R
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.RetrofitClient
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.databinding.ActivityDetallesBinding
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.entities.Pelicula
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetallesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesBinding
    private var marca = true

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val infoPel = intent.extras?.get("peliculaId") as String?
        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Detalles"

        val existePelicula: Pelicula? = intent.extras?.get("pelicula") as Pelicula?

        if (existePelicula != null) {

            val sharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
            val token = sharedPreferences.getString("token", "No encontrado")

            val llamadaApi: Call<Pelicula>? =
                infoPel?.let { RetrofitClient.apiRetrofit.getById("Bearer $token", it) }
            if (llamadaApi != null) {
                llamadaApi.enqueue(object : Callback<Pelicula> {
                    override fun onResponse(call: Call<Pelicula>, response: Response<Pelicula>) {
                        if (response.code() > 299 || response.code() < 200) {
                            Toast.makeText(
                                this@DetallesActivity,
                                "No se pudo cargar la pelicula",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val peliculaDetalles = response.body()!!

                            title = peliculaDetalles.nombre
                            binding.etTitulo.setText(peliculaDetalles.nombre)
                            binding.etDirector.setText(peliculaDetalles.director)
                            binding.etGenero.setText(peliculaDetalles.genero)
                            binding.etNota.setText(peliculaDetalles.nota.toString())
                            binding.etUrl.setText(peliculaDetalles.url)
                            binding.etTelefonoD.setText(peliculaDetalles.telefono)

                            Picasso.get().load(peliculaDetalles.url).into(binding.ivImagenDetalles)

                            binding.btLlamar.isEnabled = true
                        }
                    }

                    override fun onFailure(call: Call<Pelicula>, t: Throwable) {
                        Log.d("response: failure", t.message.toString())
                    }
                })
            }

        } else {

            title = "Añadir Película"

            binding.btLlamar.isEnabled = false

            Picasso.get()
                .load("https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Circle-icons-image.svg/512px-Circle-icons-image.svg.png")
                .into(binding.ivImagenDetalles)

            binding.etTitulo.isFocusableInTouchMode = true
            binding.etTitulo.isCursorVisible = true
            binding.etDirector.isFocusableInTouchMode = true
            binding.etDirector.isCursorVisible = true
            binding.etUrl.isFocusableInTouchMode = true
            binding.etUrl.isCursorVisible = true
            binding.etNota.isFocusableInTouchMode = true
            binding.etNota.isCursorVisible = true
            binding.etGenero.isFocusableInTouchMode = true
            binding.etGenero.isCursorVisible = true
            binding.etTelefonoD.isFocusableInTouchMode = true
            binding.etTelefonoD.isCursorVisible = true
        }

        binding.btLlamar.setOnClickListener() {
            val telefono = "+34" + binding.etTelefonoD.text.toString()
            val llamada = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", telefono, null))
            startActivity(llamada)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (title != "Añadir Película") {
            menuInflater.inflate(R.menu.menu_editar, menu)
        } else {
            menuInflater.inflate(R.menu.menu_anadir, menu)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.accion_editar) {
            if (marca) {
                binding.btLlamar.isEnabled = false

                item.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_check_24)

                binding.etTitulo.isFocusableInTouchMode = true
                binding.etTitulo.isCursorVisible = true
                binding.etDirector.isFocusableInTouchMode = true
                binding.etDirector.isCursorVisible = true
                binding.etUrl.isFocusableInTouchMode = true
                binding.etUrl.isCursorVisible = true
                binding.etNota.isFocusableInTouchMode = true
                binding.etNota.isCursorVisible = true
                binding.etGenero.isFocusableInTouchMode = true
                binding.etGenero.isCursorVisible = true
                binding.etTelefonoD.isFocusableInTouchMode = true
                binding.etTelefonoD.isCursorVisible = true

                marca = false

            } else {
                val builder = AlertDialog.Builder(this)
                val dialog = builder.setTitle("Editar pelicula")
                    .setMessage("Vas a editar una pelicula")
                    .setPositiveButton("Aceptar") { _, _ ->
                        item.icon =
                            ContextCompat.getDrawable(this, R.drawable.ic_baseline_edit_24)
                        marca = true

                        binding.etTitulo.isFocusable = false
                        binding.etDirector.isFocusable = false
                        binding.etUrl.isFocusable = false
                        binding.etNota.isFocusable = false
                        binding.etGenero.isFocusable = false
                        binding.etTelefonoD.isFocusable = false


                        val sharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
                        val token = sharedPreferences.getString("token", "No encontrado")
                        val pelicula = Pelicula(
                            binding.etTitulo.text.toString(),
                            binding.etGenero.text.toString(),
                            binding.etDirector.text.toString(),
                            binding.etNota.text.toString().toDouble(),
                            binding.etUrl.text.toString(),
                            binding.etTelefonoD.text.toString(),
                            binding.etDuracion.text.toString().toInt(),

                        )
                        val llamadaApi: Call<Unit> = RetrofitClient.apiRetrofit.actualizarPeliculas(
                            "Bearer $token",
                            pelicula
                        )
                        llamadaApi.enqueue(object : Callback<Unit> {
                            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                                if (response.code() > 299 || response.code() < 200) {
                                    Toast.makeText(
                                        this@DetallesActivity,
                                        "No se pudo actualizar",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        this@DetallesActivity,
                                        "Película actualizada",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(call: Call<Unit>, t: Throwable) {
                                Log.d("response: failure", t.message.toString())
                            }
                        })

                            finish()
                    }
                    .setNegativeButton("Cancelar", null)
                    .create()

                dialog.show()
            }

            return true

        } else if (item.itemId == R.id.accion_add) {
            if (TextUtils.equals(
                    binding.etTitulo.text.toString(),
                    ""
                ) || TextUtils.equals(
                    binding.etGenero.text.toString(),
                    ""
                ) || TextUtils.equals(
                    binding.etDirector.text.toString(),
                    ""
                ) || TextUtils.equals(
                    binding.etUrl.text.toString(),
                    ""
                ) || TextUtils.equals(
                    binding.etNota.text.toString(),
                    ""
                )
                || TextUtils.equals(
                    binding.etTelefonoD.text.toString(),
                    ""
                )
                || TextUtils.equals(
                    binding.etDuracion.text.toString(),
                    ""
                )
            ) {
                Toast.makeText(this, "Campos vacíos", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val builder = AlertDialog.Builder(this)
                val dialog = builder.setTitle("Crear nueva pelicula")
                    .setMessage("Vas a  crear una nueva pelicula")
                    .setPositiveButton("Aceptar") { _, _ ->
                        val sharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
                        val token = sharedPreferences.getString("token", "No encontrado")
                        val pelicula = Pelicula(
                            binding.etTitulo.text.toString(),
                            binding.etGenero.text.toString(),
                            binding.etDirector.text.toString(),
                            binding.etNota.text.toString().toDouble(),
                            binding.etUrl.text.toString(),
                            binding.etTelefonoD.text.toString(),
                            binding.etDuracion.text.toString().toInt()
                        )

                        val llamadaApi: Call<Unit> = RetrofitClient.apiRetrofit.createPeliculas("Bearer $token", pelicula)
                        llamadaApi.enqueue(object : Callback<Unit> {
                            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                                if (response.code() > 299 || response.code() < 200) {
                                    Toast.makeText(
                                        this@DetallesActivity,
                                        "Error al añadir la película",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        this@DetallesActivity,
                                        "Película añadida correctamente",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(call: Call<Unit>, t: Throwable) {
                                Log.d("response: failure", t.message.toString())
                            }
                        })


                        finish()
                    }
                    .setNegativeButton("Cancelar", null)
                    .create()

                dialog.show()
            }

            return true
        } else if (item.itemId == R.id.accion_borrar) {
            if (marca) {
                val pos = intent.extras?.get("pos") as String?
                val sharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
                val token = sharedPreferences.getString("token", "No encontrado")

                val builder = AlertDialog.Builder(this)
                val dialog = builder.setTitle("Borrar pelicula")
                    .setMessage("Vas a borrar una pelicula")
                    .setPositiveButton("Aceptar") { dialog, id ->
                        val llamadaApi: Call<Unit> =
                            RetrofitClient.apiRetrofit.borrarPeliculas(
                                "Bearer $token",
                                pos
                            )
                        llamadaApi.enqueue(object : Callback<Unit> {
                            override fun onResponse(
                                call: Call<Unit>,
                                response: Response<Unit>
                            ) {
                                if (response.code() > 299 || response.code() < 200) {
                                    Toast.makeText(
                                        this@DetallesActivity,
                                        "Error al borrar la película",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        this@DetallesActivity,
                                        "Película borrada",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(call: Call<Unit>, t: Throwable) {
                                Log.d("response: failure", t.message.toString())
                            }
                        })
                        finish()
                    }
                    .setNegativeButton("Cancelar", null)
                    .create()

                dialog.show()
            }

            return true
        }

        else {
            return super.onOptionsItemSelected(item)
        }
    }
}
