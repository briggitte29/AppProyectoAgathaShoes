package com.proyecto.appproyectocalzado.retrofit

import com.proyecto.appproyectocalzado.utilitarios.Constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//Creando un Objeto donde construira la instancia de rertrofit
object AgathaCliente {

    private val URL="http://192.168.1.8:9898/agathashoes/"
    
    //Conectandonos a la red
    private var okHttpClient=OkHttpClient.Builder()
        .connectTimeout(1,TimeUnit.MINUTES)
        .readTimeout(30,TimeUnit.MINUTES)
        .writeTimeout(15,TimeUnit.MINUTES)
        .build()

    //Creando la instancia
    val retrofit= Retrofit.Builder()
        .baseUrl(URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //Creando la implementacion del servicio
    val retrofitServic:AgathaService by lazy {
        retrofit.create(AgathaService::class.java)
    }

}