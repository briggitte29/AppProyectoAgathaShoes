package com.proyecto.appproyectocalzado.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.proyecto.appproyectocalzado.retrofit.AgathaCliente
import com.proyecto.appproyectocalzado.retrofit.AgathaService
import com.tienda.agathashoes.models.Pedido
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartRepository {

    var responsePedido=MutableLiveData<Int>()
    var responsePedidos=MutableLiveData<List<Pedido>>()
    var responsePedidoUpdate=MutableLiveData<Int>()

    fun agregarCarrito(pedido: Pedido):MutableLiveData<Int>{
        val response=AgathaCliente.retrofitServic.agregarPedido(pedido)
        response.enqueue(object:Callback<Int>{
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                responsePedido.value=response.body()!!
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.e("ERROR",t.message.toString())
            }

        })
        return responsePedido
    }

    fun listarPedidos():MutableLiveData<List<Pedido>>{
        val response=AgathaCliente.retrofitServic.listarPedido()
        response.enqueue(object:Callback<List<Pedido>>{
            override fun onResponse(call: Call<List<Pedido>>, response: Response<List<Pedido>>) {
                responsePedidos.value=response.body()!!
            }

            override fun onFailure(call: Call<List<Pedido>>, t: Throwable) {
                Log.e("ERRORAUTH",t.message.toString())
            }

        })
        return responsePedidos
    }

    fun updatePedido(pedido: Pedido):MutableLiveData<Int>{
        val call=AgathaCliente.retrofitServic.updatePedido(pedido)
        call.enqueue(object:Callback<Int>{
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                responsePedidoUpdate.value=response.body()!!
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.e("ERRORAUTH",t.message.toString())
            }

        })
        return  responsePedidoUpdate
    }

}