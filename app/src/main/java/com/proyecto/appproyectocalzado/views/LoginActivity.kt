package com.proyecto.appproyectocalzado.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.lifecycle.ViewModelProvider
import com.proyecto.appproyectocalzado.databinding.ActivityLoginBinding
import com.proyecto.appproyectocalzado.retrofit.request.Clientes
import com.proyecto.appproyectocalzado.retrofit.request.Rol
import com.proyecto.appproyectocalzado.utilitarios.AppMensaje
import com.proyecto.appproyectocalzado.utilitarios.TipoMensaje
import com.proyecto.appproyectocalzado.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewmodel:AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializando el viewmodel
        authViewmodel=ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            validarEntradaCorreo()
        }

        binding.tvRegistrar.setOnClickListener {
            irRegistro()
        }

        authViewmodel.responseLogin.observe(this,{ res->
            obtenerDatos(res!!)
        })

    }

    private fun obtenerDatos(clientes: Clientes) {
        if(clientes.nombre.isNotEmpty()){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }else{
            //Toast.makeText(this, "no se pudo", Toast.LENGTH_SHORT).show()
            AppMensaje.enviarMensaje(binding.root,"Correo o Clave incorrectos", TipoMensaje.ERROR)
        }
        binding.btnLogin.isEnabled=true
        binding.tvRegistrar.isEnabled=true
    }

    private fun irRegistro(){
        startActivity(Intent(this,RegistroActivity::class.java))
    }

    private fun validarCorreo(correo:String):Boolean{
        val pattern=Patterns.EMAIL_ADDRESS
        return pattern.matcher(correo).matches()
    }

    private fun validarEntradaCorreo(){
        //Desabilitando
        binding.btnLogin.isEnabled=false
        binding.tvRegistrar.isEnabled=false

        var okey=true
        if(binding.txtCorreo.text.toString().trim().isEmpty()){
            binding.txtCorreo.isFocusableInTouchMode=true
            binding.txtCorreo.requestFocus()
            okey=false

        }else if (binding.txtcontraseA.text.toString().trim().isEmpty()){
            binding.txtcontraseA.isFocusableInTouchMode=true
            binding.txtcontraseA.requestFocus()
            okey=false
        }

        if(okey){
            val correo=binding.txtCorreo.text.toString()
            val clave=binding.txtcontraseA.text.toString()
            authViewmodel.autenticar(
                Clientes(0,"",
            "",correo,"",clave,Rol(),"")
            )
            okey=false
        }else {
            binding.btnLogin.isEnabled = true
            AppMensaje.enviarMensaje(binding.root, "Ingrese Correo y Clave", TipoMensaje.ERROR)
        }

    }


}