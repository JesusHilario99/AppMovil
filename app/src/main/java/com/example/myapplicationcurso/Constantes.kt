package com.example.myapplicationcurso

import java.text.DateFormat
import java.util.Calendar
import java.util.Locale
import java.util.*

object Constantes {

    fun verTiempoDis(): Long {
        return System.currentTimeMillis()
    }

    fun obtenerFecha(tiempo:Long) : String{
        val calendario = Calendar.getInstance(Locale.ENGLISH)
        calendario.timeInMillis = tiempo

        return DateFormat.format("dd/MM/yyyy",calendario).toString()
    }
}