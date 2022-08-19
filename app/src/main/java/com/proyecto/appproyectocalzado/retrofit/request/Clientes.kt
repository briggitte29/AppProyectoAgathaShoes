package com.proyecto.appproyectocalzado.retrofit.request

import com.google.gson.annotations.SerializedName

//Creando la clase login - pedida de datos
data class Clientes (
        val idCliente:Int?,
        val nombre: String,
        val apellidos: String,
        val correo: String,
        val fono: String,
        val clave: String,
        val idRol:Rol,
        val ban:String="0"
        )