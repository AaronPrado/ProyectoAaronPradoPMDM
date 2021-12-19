package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.R
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.databinding.ActivityRegistroBinding
import java.util.regex.Pattern

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    private lateinit var btRegistrame: Button
    private lateinit var tietEmail: TextInputEditText
    private lateinit var tietContrasena: TextInputEditText
    private lateinit var tietUsuario: TextInputEditText
    private lateinit var tietNombre: TextInputEditText
    private lateinit var tietTelefono: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("Nuevo usuario")

        btRegistrame = findViewById(R.id.btnRegistrame)
        tietNombre = findViewById(R.id.tietNombre)
        tietEmail = findViewById(R.id.tietEmailLogin)
        tietContrasena = findViewById(R.id.tietContrasena)
        tietUsuario = findViewById(R.id.tietUsuario)
        tietTelefono = findViewById(R.id.tietTelefono)


        btRegistrame.setOnClickListener {
            if(comprobarDatos()){
                val sharedPrefs = getPreferences(Context.MODE_PRIVATE)
                val editor = sharedPrefs.edit()
                editor.putString("email", tietEmail.text.toString())
                editor.putString("password", tietContrasena.text.toString())

                onBackPressed()
            }
        }
    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun comprobarDatos(): Boolean {
        val pwd = tietContrasena.text.toString()

        if (!validarEmail(tietEmail.text.toString())) {
            Toast.makeText(this, "Correo incorrecto", Toast.LENGTH_SHORT).show()
            return false
        } else if (pwd.length < 8 || pwd.length > 20) {
            Toast.makeText(this, "La contraseña no tiene la longitud correcta", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }
}