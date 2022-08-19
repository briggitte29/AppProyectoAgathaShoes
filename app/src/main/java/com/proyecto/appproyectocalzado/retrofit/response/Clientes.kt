package com.tienda.agathashoes.models

data class Clientes(
    val idCliente:Int?,
    val nombre:String,
    val apellidos:String,
    val correo:String,
    val fono:String,
    val clave:String,
    val idRol:Rol?,
    val ban:String
) {
    override fun toString(): String {
        return "Clientes(idCliente=$idCliente, nombre='$nombre', apellidos='$apellidos', correo='$correo', fono='$fono', clave='$clave', idRol=$idRol, ban='$ban')"
    }
}