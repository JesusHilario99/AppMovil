package com.example.myapplicationcurso.opciones_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplicationcurso.R
import com.example.myapplicationcurso.Registro_email
import com.example.myapplicationcurso.databinding.ActivityLoginEmailBinding

class Login_Email : AppCompatActivity() {

    private lateinit var binding: ActivityLoginEmailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtRegistrarme.setOnClickListener {
            startActivity(Intent(this@Login_Email,Registro_email::class.java))
        }
    }
}