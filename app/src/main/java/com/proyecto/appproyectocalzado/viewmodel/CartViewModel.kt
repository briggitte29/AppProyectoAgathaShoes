package com.proyecto.appproyectocalzado.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.proyecto.appproyectocalzado.repository.CartRepository
import com.tienda.agathashoes.models.Pedido

class CartViewModel : ViewModel() {

    var responseAddPedido:LiveData<Int>
    var responseUpdatePedido:LiveData<Int>

    val cartRepository=CartRepository()

    init {
        responseAddPedido=cartRepository.responsePedido
        responseUpdatePedido=cartRepository.responsePedidoUpdate
    }

    fun agregarCarrito(pedido: Pedido):LiveData<Int>{
        return cartRepository.agregarCarrito(pedido)
    }

    fun listarPedido():LiveData<List<Pedido>>{
        return cartRepository.listarPedidos()
    }

    fun updatePedido(pedido: Pedido):LiveData<Int>{
        return cartRepository.updatePedido(pedido)
    }

}