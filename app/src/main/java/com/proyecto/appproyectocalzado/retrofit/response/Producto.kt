package com.proyecto.appproyectocalzado.retrofit.response

data class Producto (
    val idPro:Int,
    val nombre:String,
    val descripcion:String,
    val stock:Int,
    val imagen:String,
    val precio:Double,
    val descuento:Double,
    val habilitado:String,
    val fecha:String,
    val idCat: Categoria?,
    val idMarca:Marca?
        )

