package com.proyecto.appproyectocalzado.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.proyecto.appproyectocalzado.retrofit.AgathaCliente
import com.proyecto.appproyectocalzado.retrofit.request.Clientes
import com.proyecto.appproyectocalzado.retrofit.request.RequestRegistro
import com.proyecto.appproyectocalzado.retrofit.request.Rol
import com.proyecto.appproyectocalzado.retrofit.response.ResponseRegistro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {

    //Nos ayudara a establecer valores retornados y luego poder obtenernos /observarlos
    var responseLogin= MutableLiveData<Clientes>()
    var responseRegistro= MutableLiveData<Int>()

    //Creamos metodo auntenticar que retornara una respuesta mutable / enVivo
    fun autenticarUsuario(clientes: Clientes):MutableLiveData<Clientes> {
        val service = AgathaCliente.retrofitServic.loguearse(clientes)

        //Obtiene resultado de la peticion
        service.enqueue(object : Callback<Clientes> {
            //res
            override fun onResponse(call: Call<Clientes>, response: Response<Clientes>) {
                //Seteando el valor en tiempo real
                if(response.body()!=null){
                    val obj=response.body()!!
                    responseLogin.value = obj
                    Log.d("MSGRES", obj.toString())
                }else{
                    responseLogin.value= Clientes(null,"","","","","", Rol(),"")
                }

            }

            //Por si falla la peticion
            override fun onFailure(call: Call<Clientes>, t: Throwable) {
                Log.e("ErrorLogin", t.message.toString())
            }
        })

        //Retornara la respuesta/body
        return responseLogin
    }

    fun registrarUsuario(clientes: Clientes):MutableLiveData<Int>{
        val call = AgathaCliente.retrofitServic.registrar(clientes)
        call.enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                responseRegistro.value = response.body()!!
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.e("ErrorLogin", t.message.toString())
            }
        })

        return responseRegistro

    }


}