package com.proyecto.appproyectocalzado.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyecto.appproyectocalzado.R
import com.proyecto.appproyectocalzado.databinding.FragmentCartBinding
import com.proyecto.appproyectocalzado.viewmodel.CartViewModel
import com.proyecto.appproyectocalzado.views.adapter.CartAdapter
import com.tienda.agathashoes.models.Pedido

class CartFragment : Fragment() {

    private var _binding:FragmentCartBinding?=null
    private val binding get() = _binding!!

    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentCartBinding.inflate(inflater,container,false)
        binding.rvPedido.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        cartViewModel=ViewModelProvider(this).get(CartViewModel::class.java)

        cartViewModel.listarPedido().observe(this,{ list ->
            binding.rvPedido.adapter=CartAdapter(list, { pedido -> enviarArgumentos(pedido) })
        })

        return binding.root
    }

    private fun enviarArgumentos(pedido: Pedido){
        val bundle= bundleOf("id" to pedido.idPedido,"nombre" to pedido.idPro!!.nombre, "cantidad" to pedido.cantidad, "producto" to pedido.idPro.idPro, "talla" to pedido.talla )
        parentFragmentManager.beginTransaction()
            .replace(R.id.container,CompraFragment::class.java,bundle)
            .addToBackStack(null)
            .commit()
    }

}