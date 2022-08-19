package com.proyecto.appproyectocalzado.retrofit.request

//Clase registro
data class RequestRegistro (
    val idCliente:Int,
    val nombre: String,
    val apellidos: String,
    val correo: String,
    val fono: String,
    val clave: String,
    val idRol:Rol,
    val ban:String="0"
    )

data class Rol(
    val idRol:Int=2,
    val nombre:String="USER"
)