package com.proyecto.appproyectocalzado.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proyecto.appproyectocalzado.R
import com.proyecto.appproyectocalzado.databinding.ItemProductosBinding
import com.proyecto.appproyectocalzado.retrofit.response.Producto

class ProductoAdapter(private val list:List<Producto>,
                    private val setOnClickListener:(Producto)->Unit) : RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemProductosBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=ItemProductosBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(list[position]){
                binding.itemProNombre.text=nombre
                binding.itemProPrecio.text="S/. ${precioTotal(precio,descuento)}"
                Glide.with(holder.itemView.context)
                    .load(imagen).into(binding.itemProdImagen)
                binding.itemProdImagen.setOnClickListener {
                    setOnClickListener(this)
                }
            }

        }
    }

    private fun precioTotal(precio:Double,descuento:Double):Double{
        return (precio-(precio*(descuento/100)))
    }

    override fun getItemCount(): Int {
        return list.size
    }


}