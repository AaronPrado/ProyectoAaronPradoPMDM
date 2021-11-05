package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.R
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.databinding.ActivityRegistroBinding
import java.util.regex.Pattern

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val etmail = findViewById<EditText>(R.id.tietEmail)
        val btRegistrame = findViewById<Button>(R.id.btnRegistrame)
        val tietEmail = findViewById<TextInputEditText>(R.id.tietEmail)

        btRegistrame.setOnClickListener {
            var sharedPrefs = getPreferences(Context.MODE_PRIVATE)
            var editor = sharedPrefs.edit()
            editor.putString("email", etmail.text.toString())
            val intent = Intent(this, ListadoActivity::class.java)
            startActivity(intent)
        }


    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun comprobarDatos(): Boolean {
        val contrasena = binding.tietContrasena.text.toString()

        if (validarEmail(binding.tietEmail.text.toString())) {
            Toast.makeText(this, "Introduce un email válido", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        else{
            return true
        }

    }
}