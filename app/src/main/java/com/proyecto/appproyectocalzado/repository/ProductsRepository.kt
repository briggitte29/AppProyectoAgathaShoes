package com.proyecto.appproyectocalzado.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.proyecto.appproyectocalzado.retrofit.AgathaCliente
import com.proyecto.appproyectocalzado.retrofit.AgathaService
import com.proyecto.appproyectocalzado.retrofit.response.Producto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsRepository {

    var responseProducts = MutableLiveData<List<Producto>>()
    var responseProduct=MutableLiveData<Producto>()

    fun listarProductos():MutableLiveData<List<Producto>>{
        val call = AgathaCliente.retrofitServic.listProductos()

        call.enqueue(object:Callback<List<Producto>>{
            override fun onResponse(
                call: Call<List<Producto>>,
                response: Response<List<Producto>>
            ) {
                responseProducts.value = response.body()
            }

            override fun onFailure(call: Call<List<Producto>>, t: Throwable) {
                Log.e("ErrorProducts", t.message.toString())
            }

        })

        return  responseProducts
    }

    fun obtenerProducto(id:Int):MutableLiveData<Producto>{
        val call=AgathaCliente.retrofitServic.obtenerProducto(id)
        call.enqueue(object:Callback<Producto>{
            override fun onResponse(call: Call<Producto>, response: Response<Producto>) {
                responseProduct.value=response.body()
            }

            override fun onFailure(call: Call<Producto>, t: Throwable) {
                Log.e("ERROR",t.message.toString())
            }

        })
        return responseProduct
    }

}