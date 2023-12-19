package com.example.myapplicationcurso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplicationcurso.databinding.ActivityOpcionesdeLoginBinding
import com.example.myapplicationcurso.opciones_login.Login_Email

class OpcionesdeLogin : AppCompatActivity() {

    private lateinit var binding: ActivityOpcionesdeLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOpcionesdeLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.IngresarEmail.setOnClickListener {
            startActivity(Intent(this@OpcionesdeLogin, Login_Email::class.java))
        }
    }
}