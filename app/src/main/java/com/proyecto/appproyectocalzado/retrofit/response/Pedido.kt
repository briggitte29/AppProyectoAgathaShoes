package com.tienda.agathashoes.models

import com.proyecto.appproyectocalzado.retrofit.response.Producto
import java.util.*

data class Pedido(
    val idPedido:Int?,
    val idCliente:Clientes?,
    var fPedido:String,
    var fEntrega:String,
    var direccion:String,
    var hubicacionLatitud:String,
    var hubicacionLongitud:String,
    var referencia:String?,
    var distrito:String,
    var departamento:String?,
    var idEmp:Empleado?,
    val idPro:Producto?,
    var cantidad:Int,
    var talla: String?
        ){
    override fun toString(): String {
        return "Pedido(idPedido=$idPedido, idCliente=$idCliente, fPedido=$fPedido, fEntrega=$fEntrega, direccion='$direccion', hubicacionLatitud='$hubicacionLatitud', hubicacionLongitud='$hubicacionLongitud', referencia=$referencia, distrito='$distrito', departamento=$departamento, idEmp=$idEmp, idPro=$idPro, cantidad=$cantidad, talla='$talla')"
    }
}