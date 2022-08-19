package com.proyecto.appproyectocalzado.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proyecto.appproyectocalzado.databinding.ItemPedidosBinding
import com.proyecto.appproyectocalzado.retrofit.response.Producto
import com.tienda.agathashoes.models.Pedido

class CartAdapter(private val lista:List<Pedido>,
                    private val setOnClickListener:(Pedido)->Unit) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(val binding:ItemPedidosBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=ItemPedidosBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(lista[position]){
                binding.pedidoNombre.text=idPro!!.nombre
                binding.pedidoPrecio.text=calculo(idPro.precio,cantidad).toString()
                binding.talla.text=talla
                binding.pedidoCantidad.text=cantidad.toString()
                binding.pedidoDescuento.text=idPro.descuento.toString()
                Glide.with(holder.itemView.context).load(idPro.imagen).into(binding.pedidoImagen)

                holder.itemView.setOnClickListener {
                    setOnClickListener(this)
                }

            }

        }
    }

    private fun calculo(precio:Double,cantidad:Int):Double {
        return precio*cantidad
    }

    override fun getItemCount(): Int {
        return lista.size
    }


}