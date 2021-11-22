package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.R

class LoginActivity : AppCompatActivity() {

    private lateinit var btRegistro : Button
    private lateinit var btInicio : Button
    private lateinit var tietEmail: TextInputEditText
    private lateinit var tietPwd: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPreferences = getSharedPreferences("datos", Context.MODE_PRIVATE)

        btRegistro = findViewById(R.id.btnRegistro)
        btInicio = findViewById(R.id.btnInicio)
        tietEmail = findViewById(R.id.tietEmail)
        tietPwd = findViewById(R.id.tietContrasena)

        val email = sharedPreferences.getString("email", "usuario@gmail.com")
        val pwd = sharedPreferences.getString("password", "contraseña")

        tietEmail.setText(email)
        tietPwd.setText(pwd)


        btRegistro.setOnClickListener{
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
        btInicio.setOnClickListener{
            val intent = Intent(this, ListadoActivity::class.java)
            startActivity(intent)

        }

    }
}