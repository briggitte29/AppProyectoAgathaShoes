package com.tienda.agathashoes.models

data class Empleado (
    val idEmp:Int,
    val nombre:String,
    val apellido:String,
    val ciudad:String,
    val disponibilidad:String,
    val fono:String,
    val cargo:String,
    val sueldo:Double
        )