package com.proyecto.appproyectocalzado.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.proyecto.appproyectocalzado.repository.ProductsRepository
import com.proyecto.appproyectocalzado.retrofit.response.Producto

class ProductsViewModel:ViewModel() {

    //Instanciando la clase repository
    private var repository=ProductsRepository()

    //Llamando al metodo de la instancia repository
    fun listarProductos():LiveData<List<Producto>>{
        return repository.listarProductos()
    }

    fun obtenerProducto(id:Int):LiveData<Producto>{
        return repository.obtenerProducto(id)
    }

}