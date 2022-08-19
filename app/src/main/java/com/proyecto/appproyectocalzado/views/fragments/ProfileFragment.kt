package com.proyecto.appproyectocalzado.views.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.proyecto.appproyectocalzado.R
import com.proyecto.appproyectocalzado.databinding.FragmentCartBinding
import com.proyecto.appproyectocalzado.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding?=null
    private val binding get() = _binding!!

    private val CODIGO_CAMERA=1
    private val CODIGO_STORAGE=2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentProfileBinding.inflate(inflater,container,false)

        binding.cardImageUser.setOnClickListener {
            //Verificar los permisos
            if(!verificarCamara()){
                pedirPermisosCamara()
            }else{
                abrirCamara()
            }
        }
        //
        binding.btnSalir.setOnClickListener {
            activity?.finish()
        }

        return binding.root
    }

    private fun abrirCamara() {
        val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,CODIGO_CAMERA)
    }

    //Primero verificar si tiene los permisos permitidos de Camara
    private fun verificarCamara():Boolean{
        return context?.let { ContextCompat.checkSelfPermission(it,Manifest.permission.CAMERA) } ==PackageManager.PERMISSION_GRANTED
    }

    //Mostrara un panel donde debera habilitar los permisoso
    private fun pedirPermisosCamara(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(context as Activity,Manifest.permission.CAMERA) == true){
            Toast.makeText(context, "Permisos de Camara Rechazados", Toast.LENGTH_SHORT).show()
        }else{
            //Pedir permisos
            activity?.let { ActivityCompat.requestPermissions(it, arrayOf(Manifest.permission.CAMERA),CODIGO_CAMERA) }
        }
    }

    //Verificando el resultado de las peticiones de codigo
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            CODIGO_CAMERA ->{
                if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //Una vez aceptado los permisos, verifica si acepto los permisos
                    abrirCamara()
                }else{
                    pedirPermisosCamara()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CODIGO_CAMERA && resultCode==Activity.RESULT_OK){
            if(data!!.extras?.get("data")!=null){
                val img=data.extras?.get("data") as Bitmap
                binding.imgUser.setImageBitmap(img)
            }

        }
    }

}