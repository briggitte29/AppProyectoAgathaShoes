package com.proyecto.appproyectocalzado.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.proyecto.appproyectocalzado.repository.AuthRepository
import com.proyecto.appproyectocalzado.retrofit.request.Clientes
import com.proyecto.appproyectocalzado.retrofit.request.RequestRegistro
import com.proyecto.appproyectocalzado.retrofit.response.ResponseRegistro

class AuthViewModel:ViewModel() {

    //Creando objeto de tipo
    var responseLogin:LiveData<Clientes>
    var responseRegistro:LiveData<Int>

    var authRepository=AuthRepository()

    //Inicializando valores
    init {
        responseLogin=authRepository.responseLogin
        responseRegistro=authRepository.responseRegistro
    }

    //Metodo de autenticacion donde guardara el valor retornada / live
    fun autenticar(clientes: Clientes){
        responseLogin=authRepository.autenticarUsuario(clientes)
    }

    fun registrarUsuario(clientes: Clientes){
        responseRegistro=authRepository.registrarUsuario(clientes)
    }

}