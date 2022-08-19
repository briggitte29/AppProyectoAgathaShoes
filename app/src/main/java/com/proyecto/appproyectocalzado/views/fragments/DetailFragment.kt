package com.proyecto.appproyectocalzado.views.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.proyecto.appproyectocalzado.R
import com.proyecto.appproyectocalzado.databinding.FragmentDetailBinding
import com.proyecto.appproyectocalzado.retrofit.response.Categoria
import com.proyecto.appproyectocalzado.retrofit.response.Marca
import com.proyecto.appproyectocalzado.retrofit.response.Producto
import com.proyecto.appproyectocalzado.viewmodel.CartViewModel
import com.proyecto.appproyectocalzado.viewmodel.ProductsViewModel
import com.tienda.agathashoes.models.Pedido

class DetailFragment : Fragment() {

    private var _binding:FragmentDetailBinding?=null
    private val binding get() = _binding!!
    private lateinit var productsViewModel: ProductsViewModel
    private lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentDetailBinding.inflate(inflater,container,false)
        cartViewModel=ViewModelProvider(this).get(CartViewModel::class.java)
        productsViewModel=ViewModelProvider(this).get(ProductsViewModel::class.java)
        if(arguments!=null){
            val argumento:Int=arguments!!.getInt("id")
            productsViewModel.obtenerProducto(argumento).observe(this,{ pro ->
                mostrarDatos(pro)
            })
        }

        binding.btnAgregarCarrito.setOnClickListener {

            val pedido=Pedido(null,null,"","","","","",
                                "","","",null,Producto(requireArguments().getInt("id"),
                "","",0,"",0.0,0.0,"","", Categoria(0,
                    "","","",""), Marca(0,0,"","")),
                binding.txtCantidad.text.toString().toInt(),obtenerTalla())
            cartViewModel.agregarCarrito(pedido).observe(this, { res ->
                if(res == 1){
                    view?.let { it1 -> Snackbar.make(it1,"Agregado al Carrito",Snackbar.LENGTH_SHORT).show() }
                }
            })
        }



        return binding.root
    }

    private fun mostrarDatos(producto: Producto){
        binding.nombre.text=producto.nombre
        binding.categoria.text= producto.idCat?.nombre
        Glide.with(binding.img.context).load(producto.imagen).into(binding.img)
        binding.descuento.text=producto.descuento.toString()+" %"
        binding.txtPrecio.text="S/. ${producto.precio}"
    }

    private fun obtenerTalla():String{
        val radios=binding.groupRadio.checkedRadioButtonId
        val radio:RadioButton=binding.groupRadio.findViewById(radios)
        return radio.text.toString()
    }

}