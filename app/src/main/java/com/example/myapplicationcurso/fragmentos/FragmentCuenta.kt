package com.example.myapplicationcurso.fragmentos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myapplicationcurso.OpcionesdeLogin
import com.example.myapplicationcurso.R
import com.example.myapplicationcurso.databinding.ActivityLoginEmailBinding
import com.example.myapplicationcurso.databinding.FragmentCuentaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.core.Context

class FragmentCuenta : Fragment() {

    private lateinit var binding: FragmentCuentaBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var mContext : android.content.Context

    override fun onAttach(context: android.content.Context) {
        mContext = context
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCuentaBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnCerrarSesion.setOnClickListener{
            firebaseAuth.signOut()
            startActivity(Intent(mContext,OpcionesdeLogin::class.java))
            activity?.finishAffinity()
        }
    }

}