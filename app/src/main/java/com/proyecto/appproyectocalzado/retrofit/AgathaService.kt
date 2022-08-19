package com.proyecto.appproyectocalzado.retrofit

import com.proyecto.appproyectocalzado.retrofit.request.Clientes
import com.proyecto.appproyectocalzado.retrofit.request.RequestRegistro
import com.proyecto.appproyectocalzado.retrofit.response.Producto
import com.proyecto.appproyectocalzado.retrofit.response.ResponseRegistro
import com.tienda.agathashoes.models.Pedido
import retrofit2.Call
import retrofit2.http.*

//Interfaz de servicios
interface AgathaService {

    //Retornara una resultado, el cual ejecutara la peticion
    @POST("clientes/iniciar")
    fun loguearse(@Body clientes: Clientes):Call<Clientes>

    //Nos permitira ejecutar la peticion el cual retornara un resiltado del tipo dado
    @PUT("clientes")
    fun registrar(@Body clientes: Clientes):Call<Int>

    @GET("producto")
    fun listProductos():Call<List<Producto>>

    @GET("producto/{id}")
    fun obtenerProducto(@Path("id") id:Int):Call<Producto>

    @POST("pedido")
    fun agregarPedido(@Body pedido: Pedido):Call<Int>

    @GET("pedido")
    fun listarPedido():Call<List<Pedido>>

    @PUT("pedido")
    fun updatePedido(@Body pedido: Pedido):Call<Int>

}