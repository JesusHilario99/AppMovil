package com.example.myapplicationcurso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.myapplicationcurso.databinding.ActivityMainBinding
import com.example.myapplicationcurso.fragmentos.FragmentChats
import com.example.myapplicationcurso.fragmentos.FragmentCuenta
import com.example.myapplicationcurso.fragmentos.FragmentInicio
import com.example.myapplicationcurso.fragmentos.FragmentMisAnuncios

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        verFragmentInicio()

        binding.buttomNavView.setOnItemSelectedListener {item->
            when(item.itemId){
                R.id.item_inicio->{
                    verFragmentInicio()
                    true
                }
                R.id.item_chat->{
                    verFragmentChats()
                    true
                }
                R.id.item_mis_anuncios->{
                    verFragmentMisAnuncios()
                    true
                }
                R.id.item_cuenta->{
                    verFragmentCuenta()
                    true
                }
                else->{
                false
                }
            }

        }

    }

    private fun verFragmentInicio(){
        binding.tituloRL.text = "Inicio"
        val fragment = FragmentInicio()
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(binding.fragmentlayout1.id, fragment, "FragmentInicio")
        fragmentTransition.commit()
    }

    private fun verFragmentChats(){
        binding.tituloRL.text = "Chats"
        val fragment = FragmentChats()
        val FragmentTransition = supportFragmentManager.beginTransaction()
        FragmentTransition.replace(binding.fragmentlayout1.id, fragment, "FragmentChats")
        FragmentTransition.commit()
    }

    private fun verFragmentMisAnuncios(){
        binding.tituloRL.text = "Mis anuncios"
        val fragment = FragmentMisAnuncios()
        val FragmentTransition = supportFragmentManager.beginTransaction()
        FragmentTransition.replace(binding.fragmentlayout1.id, fragment, "FragmentMisAnuncios")
        FragmentTransition.commit()
    }

    private fun verFragmentCuenta(){
        binding.tituloRL.text = "Cuenta"
        val fragment = FragmentCuenta()
        val FragmentTransition = supportFragmentManager.beginTransaction()
        FragmentTransition.replace(binding.fragmentlayout1.id, fragment,"FragmentCuenta")
        FragmentTransition.commit()
    }

}