package com.example.myapplicationcurso.opciones_login

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.util.Patterns
import android.widget.Toast
import com.example.myapplicationcurso.MainActivity
import com.example.myapplicationcurso.R
import com.example.myapplicationcurso.Registro_email
import com.example.myapplicationcurso.databinding.ActivityLoginEmailBinding
import com.google.firebase.auth.FirebaseAuth

class Login_Email : AppCompatActivity() {

    private lateinit var binding: ActivityLoginEmailBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnIngresar.setOnClickListener{
            validarInfoIniciarSesion()
        }

        binding.txtRegistrarme.setOnClickListener {
            startActivity(Intent(this@Login_Email,Registro_email::class.java))
        }
    }

    private var email = ""
    private var password = ""
    private fun validarInfoIniciarSesion() {
        email = binding.etEmail.text.toString().trim()
        password = binding.etContraseA.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmail.error = "Email Invalido"
            binding.etEmail.requestFocus()
        }
        else if(email.isEmpty()){
            binding.etEmail.error = "Ingrese un Correo"
            binding.etEmail.requestFocus()
        }
        else if(password.isEmpty()){
            binding.etContraseA.error = "Ingrese una contraseña"
            binding.etContraseA.requestFocus()
        }
        else{
            LoginUsuario()
        }

    }

    private fun LoginUsuario() {
        progressDialog.setMessage("Iniciando Sesion")
        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                startActivity(Intent(this,MainActivity::class.java))
                finishAffinity()
                Toast.makeText(this, "Bienvenido!", Toast.LENGTH_SHORT).show()
            }

            .addOnFailureListener { e->
                progressDialog.dismiss()
                Toast.makeText(this,"Ocurrio un error ${e.message}", Toast.LENGTH_SHORT).show()
            }

    }
}