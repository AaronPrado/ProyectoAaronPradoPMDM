package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.App
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.R
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.databinding.ActivityDetallesBinding
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.entities.Pelicula
import com.squareup.picasso.Picasso

class DetallesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesBinding
    private var marca = true

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        title = "Detalles"

        val existePelicula: Pelicula? = intent.extras?.get("pelicula") as Pelicula?

        if (existePelicula != null) {

            title = existePelicula.titulo

            binding.etTitulo.setText(existePelicula.titulo)
            binding.etDirector.setText(existePelicula.director)
            binding.etGenero.setText(existePelicula.genero)
            binding.etNota.setText(existePelicula.valoracion.toString())
            binding.etTelefonoD.setText(existePelicula.telefono.replace(" ", ""))
            Picasso.get().load(existePelicula.imagen).into(binding.ivImagenDetalles)

            binding.btLlamar.isEnabled = true

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

            override fun onCreateOptionsMenu(menu: Menu?): Boolean {
                if (title != "Añadir Película") {
                    menuInflater.inflate(R.menu.menu_editar, menu)
                } else {
                    menuInflater.inflate(R.menu.menu_anadir, menu)
                }

                return true
            }

            override fun onOptionsItemSelected(item: MenuItem): Boolean {
                if (item.itemId == R.id.accion_editar) { // Guardar, action bar
                    if (marca) {
                        binding.btLlamar.isEnabled = false

                        item.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_check_24)

                        binding.etTitulo.isFocusableInTouchMode = true
                        binding.etTitulo.isCursorVisible = true

                        binding.etDirector.isFocusableInTouchMode = true
                        binding.etDirector.isCursorVisible = true

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
                            .setMessage("Estás a punto de editar una pelicula")
                            .setPositiveButton("Aceptar") { dialog, id ->
                                item.icon =
                                    ContextCompat.getDrawable(this, R.drawable.ic_baseline_edit_24)
                                marca = true

                                binding.etTitulo.isFocusable = false
                                binding.etDirector.isFocusable = false
                                binding.etNota.isFocusable = false
                                binding.etGenero.isFocusable = false
                                binding.etTelefonoD.isFocusable = false


                            }
                            .setNegativeButton("Cancelar", null)
                            .create()

                        dialog.show()
                    }

                    return true
                } else if (item.itemId == R.id.accion_borrar) { // Borrar, action bar
                    if (marca) {
                        val builder = AlertDialog.Builder(this)
                        val dialog = builder.setTitle("Borrar pelicula")
                            .setMessage("Estás a punto de borrar una pelicula")
                            .setPositiveButton("Aceptar") { dialog, id ->
                                val position: Int? = intent.extras?.get("position") as Int?
                                if (position != null) {
                                    App.peliculas.removeAt(position)
                                }

                                finish()
                            }
                            .setNegativeButton("Cancelar", null)
                            .create()

                        dialog.show()
                    }

                    return true
                } else if (item.itemId == R.id.accion_add) { // Añade una nueva película
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
                            binding.etNota.text.toString(),
                            ""
                        )
                        || TextUtils.equals(
                            binding.etTelefonoD.text.toString(),
                            ""
                        )
                    ) {
                        Toast.makeText(this, "Uno de los campos está vacío", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        App.peliculas.add(Pelicula(
                                binding.etTitulo.text.toString(),
                                binding.etGenero.text.toString(),
                                binding.etDirector.text.toString(),
                                binding.etNota.text.toString(),
                                binding.etTelefonoD.text.toString()
                            )
                        )

                        finish()
                    }

                    return true
                } else {
                    return super.onOptionsItemSelected(item)
                }
            }
        }
    }
}
