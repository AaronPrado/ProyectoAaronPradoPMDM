package com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.R
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.RetrofitClient
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.entities.Token
import com.murallaromana.dam.segundo.pradodarribaaaronproyectopmdm.modelo.entities.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var btRegistro: Button
    private lateinit var btInicio: Button
    private lateinit var tietEmailLogin: TextInputEditText
    private lateinit var tietContrasenaLogin: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        setContentView(R.layout.activity_login)
        title = "Inicio de Sesión"
        val sharedPreferences = getSharedPreferences("datos", Context.MODE_PRIVATE)

        btRegistro = findViewById(R.id.btnRegistro)
        btInicio = findViewById(R.id.btnInicio)
        tietEmailLogin = findViewById(R.id.tietEmailLogin)
        tietContrasenaLogin = findViewById(R.id.tietContrasenaLogin)


        btRegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
            finish()
        }

        btInicio.setOnClickListener {
            val user = Usuario(tietEmailLogin.text.toString(), tietContrasenaLogin.text.toString())
            val loginCall = RetrofitClient.apiRetrofit.login(user)
            loginCall.enqueue(object : Callback<Token> {
                override fun onFailure(call: Call<Token>, t: Throwable) {
                    Log.d("respuesta: onFailure", t.toString())
                }

                @SuppressLint("CommitPrefEdits")
                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    Log.d("respuesta: onResponse", response.toString())

                    if (response.code() > 299 || response.code() < 200) {
                        btInicio.isEnabled = true
                        Toast.makeText(
                            this@LoginActivity,
                            "Usuario no registrado",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        val token = response.body()?.token
                        Log.d("respuesta: token:", token.orEmpty())

                        val editor = sharedPreferences.edit()
                        editor.putString("token", token)
                        editor.putString("codeResponse", response.code().toString())
                        editor.apply()

                        val inicio = Intent(this@LoginActivity, ListadoActivity::class.java)
                        startActivity(inicio)
                        finish()
                    }
                }
            })
        }
    }
}


