package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.activities

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.R
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.RetrofitClient
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.databinding.ActivityRegistroBinding
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.entities.Usuario
import java.util.regex.Pattern
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val context = this

        binding.btnRegistrame.setOnClickListener {

            if (comprobarDatos()) {
                val email = binding.tietEmail.text.toString()
                val pwd = binding.tietContrasena.text.toString()

                val call: Call<Usuario> = RetrofitClient.apiRetrofit.signup(Usuario(email,pwd))

                call.enqueue(object : Callback<Usuario> {
                    override fun onResponse(call: Call<Usuario>,response: Response<Usuario>) {

                        if (response.isSuccessful) {
                            Toast.makeText(this@RegistroActivity, "Usuario creado con éxito", Toast.LENGTH_SHORT)
                                .show()
                                val intent = Intent(context, LoginActivity::class.java)
                                startActivity(intent)
                        }
                        else {
                            Toast.makeText(this@RegistroActivity, "Error al crear el usuario", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<Usuario>, t: Throwable) {
                        Log.d("respuesta: onFailure", t.toString())
                    }
                })
            }
        }
    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun comprobarDatos(): Boolean {
        val pwd = binding.tietContrasena.text.toString()

        if (validarEmail(binding.tietEmail.text.toString()) == false) {
            Toast.makeText(this, "Email no válido", Toast.LENGTH_SHORT).show()
            return false
        } else if (pwd.length < 5 || pwd.length > 10) {
            Toast.makeText(this, "Longitud de contraseña incorrecta", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}