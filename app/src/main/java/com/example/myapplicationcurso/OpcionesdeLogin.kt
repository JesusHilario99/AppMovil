package com.example.myapplicationcurso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplicationcurso.databinding.ActivityOpcionesdeLoginBinding
import com.example.myapplicationcurso.opciones_login.Login_Email
import com.google.firebase.auth.FirebaseAuth

class OpcionesdeLogin : AppCompatActivity() {

    private lateinit var binding: ActivityOpcionesdeLoginBinding

    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOpcionesdeLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        comprobarsesion()

        binding.IngresarEmail.setOnClickListener {
            startActivity(Intent(this@OpcionesdeLogin, Login_Email::class.java))
        }
    }

    private fun comprobarsesion(){
        if (firebaseAuth.currentUser !=null){
            startActivity(Intent(this,MainActivity::class.java))
            finishAffinity()
        }
    }


}