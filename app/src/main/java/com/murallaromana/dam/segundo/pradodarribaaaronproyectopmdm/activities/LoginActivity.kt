package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.R

class LoginActivity : AppCompatActivity() {

    private lateinit var btRegistro: Button
    private lateinit var btInicio: Button
    private lateinit var tietEmailLogin: TextInputEditText
    private lateinit var tietContrasenaLogin: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        title = "Login"

        val sharedPreferences = getSharedPreferences("datos", Context.MODE_PRIVATE)

        btRegistro = findViewById(R.id.btnRegistro)
        btInicio = findViewById(R.id.btnInicio)
        tietEmailLogin = findViewById(R.id.tietEmailLogin)
        tietContrasenaLogin = findViewById(R.id.tietContrasenaLogin)

        val email = sharedPreferences.getString("email", "nombreusuario@gmail.com")
        val pwd = sharedPreferences.getString("password", "contraseña")

        tietEmailLogin.setText(email)
        tietContrasenaLogin.setText(pwd)


        btRegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
        btInicio.setOnClickListener {
            val intent = Intent(this, ListadoActivity::class.java)
            startActivity(intent)

        }
    }
}