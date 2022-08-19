package com.proyecto.appproyectocalzado.views.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.google.android.material.textfield.TextInputLayout
import com.proyecto.appproyectocalzado.R
import com.proyecto.appproyectocalzado.databinding.FragmentCartBinding
import com.proyecto.appproyectocalzado.databinding.FragmentCompraBinding
import com.proyecto.appproyectocalzado.retrofit.response.Producto
import com.proyecto.appproyectocalzado.viewmodel.CartViewModel
import com.tienda.agathashoes.models.Pedido
import java.text.SimpleDateFormat
import java.util.*

class CompraFragment : Fragment() {

    private var _binding: FragmentCompraBinding?=null
    private val binding get() = _binding!!
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentCompraBinding.inflate(inflater,container,false)
        cartViewModel=ViewModelProvider(this).get(CartViewModel::class.java)

        if(arguments!=null){
            val id=requireArguments().getInt("id")
            val nombre=requireArguments().getString("nombre")
            val cantidad=requireArguments().getInt("cantidad")

            binding.itemsCarrito.text="• Pedido: ${id} - Nombre: ${nombre} - Cantidad: ${cantidad} "

        }

        binding.btnContinuarCompra.setOnClickListener {
            if(validarDatosEnvio()){
                if(arguments!=null){
                    val arg=requireArguments().getInt("id")
                    val idproducto=requireArguments().getInt("producto")
                    val producto=Producto(idproducto,"","",0,"",0.0,0.0,"","",null,null)
                    val pedido=Pedido(arg,null,binding.pedidoFecha.text.toString(),
                        binding.entregaFecha.text.toString(),binding.txtDireccion.text.toString(),
                        "","",binding.txtReferencia.text.toString(),
                        binding.txtDistrito.text.toString(),binding.txtDepartamento.text.toString(),
                        null,producto,requireArguments().getInt("cantidad"),requireArguments().getString("talla"));

                    cartViewModel.updatePedido(pedido)

                }
            }
        }

        //Iniciar Picker
        val builder=MaterialDatePicker.Builder.datePicker()
        builder.setTitleText("Fecha de Entrega")
        val picker=builder.build()

        picker.addOnPositiveButtonClickListener(object:MaterialPickerOnPositiveButtonClickListener<Long>{
            override fun onPositiveButtonClick(selection: Long?) {
                binding.entregaFecha.text=picker.headerText
            }
        })

        binding.entregaFecha.setOnClickListener {
            picker.show(parentFragmentManager,"DATA-PICKER")
        }

        //Fecha actual al textview
        val smp=SimpleDateFormat("yyyy-MM-dd")
        binding.pedidoFecha.text=smp.format(fechaActual())

        binding.btnMapa.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container,MapsFragment())
                .addToBackStack(null)
                .commit()
        }

        //CartviewModel observador a la esucha del resultado
        cartViewModel.responseUpdatePedido.observe(this,{res ->
            if(res==1){
                mostrarAlertaCompra()
            }
        })

        return binding.root
    }

    private fun mostrarAlertaCompra(){
        val alert=AlertDialog.Builder(context)
            .setTitle("Compra")
            .setMessage("Compra Realiza con Exito")
            .setNeutralButton("Aceptar",{ dialog,i->
                parentFragmentManager.popBackStack()
            })
        alert.create().show()
    }

    //Si todos los datos son correctos o verdaderos
    private fun validarDatosEnvio():Boolean{
        if(validarCampo(binding.txtDistrito.text.toString(),binding.pedDistrito)
            && validarCampo(binding.txtDireccion.text.toString(),binding.pedDireccion)
            && validarCampo(binding.txtDepartamento.text.toString(),binding.pedDepartamento) && validarFechaEntrega()){
            return true
        }
        return false
    }

    private fun validarCampo(campo:String,layoutText: TextInputLayout):Boolean{
        //Si esta vacio
        if(campo.isEmpty()){
            layoutText.error="Campo Necesario"
            return false
        }
        layoutText.error=""
        return true
    }

    private fun validarFechaEntrega():Boolean{
        val texto=binding.entregaFecha.text
        if(texto.isEmpty()){
            val alert=AlertDialog.Builder(context)
                .setMessage("Selecciona una Fecha de Entrega")
                .create()
            alert.show()
            return false
        }
        return true
    }

    private fun fechaActual():Date{
        val long=System.currentTimeMillis()
        val date=Date(long)
        return date
    }

}