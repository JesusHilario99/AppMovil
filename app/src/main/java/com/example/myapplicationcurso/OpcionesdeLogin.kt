package com.example.myapplicationcurso

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myapplicationcurso.databinding.ActivityOpcionesdeLoginBinding
import com.example.myapplicationcurso.opciones_login.Login_Email
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception

class OpcionesdeLogin : AppCompatActivity() {

    private lateinit var binding: ActivityOpcionesdeLoginBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    private lateinit var mGooglesingInClient : GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOpcionesdeLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()
        comprobarsesion()

        val google = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGooglesingInClient = GoogleSignIn.getClient(this,google)




        binding.IngresarEmail.setOnClickListener {
            startActivity(Intent(this@OpcionesdeLogin, Login_Email::class.java))
        }
        binding.IngresarGoogle.setOnClickListener{
            googleLogin()
        }
    }

    private fun googleLogin() {
        val googleSingInIntent = mGooglesingInClient.signInIntent
        googleSingInArl.launch(googleSingInIntent)
    }
    private val googleSingInArl = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){resultado->
        if(resultado.resultCode == RESULT_OK){
            val data = resultado.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
               val cuenta = task.getResult(ApiException::class.java)
                autenticacionconGoogle(cuenta.idToken)
            }catch (e:Exception){
                Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun autenticacionconGoogle(idToken: String?) {
        val credencial = GoogleAuthProvider.getCredential(idToken,null)
        firebaseAuth.signInWithCredential(credencial)
            .addOnSuccessListener {resultadoAuth->
                if (resultadoAuth.additionalUserInfo!!.isNewUser){
                    llenarinfoDB()
                }
                else{
                    startActivity(Intent(this,MainActivity::class.java))
                    finishAffinity()
                }

            }
            .addOnFailureListener {e->
                Toast.makeText(this, "Fallo ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun llenarinfoDB() {
        progressDialog.setMessage("Guardando Datos")

        val tiempo = Constantes.verTiempoDis()
        val emailUser = firebaseAuth.currentUser!!.email
        val uidUser = firebaseAuth.uid
        val nombreUsuario = firebaseAuth.currentUser?.displayName

        val hashMap = HashMap<String, Any>()
        hashMap["nombres"] = "${nombreUsuario}"
        hashMap["codigoTelefono"] = ""
        hashMap["telefono"] = ""
        hashMap["urlImagenPerfil"] = ""
        hashMap["proveedor"] = "Google"
        hashMap["escribiendo"] = ""
        hashMap["tiempo"] = tiempo
        hashMap["email"] = "${emailUser}"
        hashMap["uid"] = "${uidUser}"
        hashMap["fecha_nac"] = ""


        val ref = FirebaseDatabase.getInstance().getReference("Usuarios")
        ref.child(uidUser!!)
            .setValue(hashMap)
            .addOnSuccessListener {
                progressDialog.dismiss()
                startActivity(Intent(this,MainActivity::class.java))
                finishAffinity()
            }
            .addOnFailureListener{e->
                progressDialog.dismiss()
                Toast.makeText(this,"No se registro debido a ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun comprobarsesion(){
        if (firebaseAuth.currentUser !=null){
            startActivity(Intent(this,MainActivity::class.java))
            finishAffinity()
        }
    }


}