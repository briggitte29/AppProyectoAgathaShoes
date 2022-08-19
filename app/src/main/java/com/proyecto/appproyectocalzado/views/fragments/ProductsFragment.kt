package com.proyecto.appproyectocalzado.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.proyecto.appproyectocalzado.R
import com.proyecto.appproyectocalzado.databinding.FragmentProductsBinding
import com.proyecto.appproyectocalzado.retrofit.response.Producto
import com.proyecto.appproyectocalzado.viewmodel.ProductsViewModel
import com.proyecto.appproyectocalzado.views.adapter.ProductoAdapter

class ProductsFragment : Fragment() {

    private var _binding:FragmentProductsBinding?=null
    private val binding get() = _binding!!
    private lateinit var productsViewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentProductsBinding.inflate(inflater,container,false)
        binding.rvProductos.layoutManager=GridLayoutManager(context,2)
            productsViewModel=ViewModelProvider(this).get(ProductsViewModel::class.java)

        productsViewModel.listarProductos().observe(this,{ list ->
            binding.rvProductos.adapter=ProductoAdapter(list) { product -> obtener(product) }
        })

        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.carrito->{
                    redireccionar(CartFragment())
                    true
                }
                R.id.perfil->{
                    redireccionar(ProfileFragment())
                    true
                }
                else -> false
            }
        }

        return binding.root
    }

    private fun redireccionar(fragment: Fragment){
        parentFragmentManager.beginTransaction()
            .replace(R.id.container,fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun obtener(producto: Producto){
        //Pasando argumentos
        val argumentos= bundleOf("id" to producto.idPro)
        //Reemplazando fragmento
        parentFragmentManager.beginTransaction()
            .replace(R.id.container,DetailFragment::class.java,argumentos)
            .addToBackStack(null)
            .commit()
    }


}