package com.example.myapplicationcurso

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.myapplicationcurso.databinding.ActivityRegistroEmailBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class Registro_email : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroEmailBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por Favor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnRegistrar.setOnClickListener{
            validarInfo()
        }
    }


    private var email = ""
    private var password = ""
    private var r_password = ""

    private fun validarInfo(){
        email = binding.etEmail.text.toString().trim()
        password = binding.etContraseA.text.toString().trim()
        r_password = binding.etRPassword.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmail.error = "Email invalido"
            binding.etEmail.requestFocus()
        }
        else if(email.isEmpty()){
            binding.etEmail.error = "Ingrese un correo valido"
            binding.etEmail.requestFocus()
        }
        else if(password.isEmpty()){
            binding.etContraseA.error = "Ingrese una contraseña"
            binding.etContraseA.requestFocus()
        }
        else if(r_password.isEmpty()){
            binding.etRPassword.error = "Escriba de nuevo su contraseña"
            binding.etRPassword.requestFocus()
        }
        else if(password != r_password){
            binding.etRPassword.error = "Escriba la misma contraseña"
            binding.etRPassword.requestFocus()
        }
        else{
            registrarUser()
        }
    }


    private fun registrarUser(){
        progressDialog.setCancelMessage("Creando cuenta")
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                llenarInfoBD()
            }
            .addOnFailureListener{e->
                progressDialog.dismiss()
                Toast.makeText(this, "No se registro el usuario debido a ${e.message}",Toast.LENGTH_SHORT).show()
            }
    }

    private fun llenarInfoBD(){
        progressDialog.setMessage("Guardando Datos")

        val tiempo = Constantes.verTiempoDis()
        val emailUser = firebaseAuth.currentUser!!.email
        val uidUser = firebaseAuth.uid

        val hashMap = HashMap<String, Any>()
        hashMap["nombres"] = ""
        hashMap["codigoTelefono"] = ""
        hashMap["telefono"] = ""
        hashMap["urlImagenPerfil"] = ""
        hashMap["proveedor"] = "Email"
        hashMap["escribiendo"] = ""
        hashMap["tiempo"] = tiempo
        hashMap["email"] = "${emailUser}"
        hashMap["uid"] = "${uidUser}"
        hashMap["fecha_nac"] = ""



    }



}