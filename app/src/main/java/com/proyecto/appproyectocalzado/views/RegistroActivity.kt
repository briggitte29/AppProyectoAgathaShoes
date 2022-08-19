package com.proyecto.appproyectocalzado.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.proyecto.appproyectocalzado.R
import com.proyecto.appproyectocalzado.databinding.ActivityRegistroBinding
import com.proyecto.appproyectocalzado.retrofit.request.Clientes
import com.proyecto.appproyectocalzado.retrofit.request.Rol
import com.proyecto.appproyectocalzado.viewmodel.AuthViewModel

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegistroBinding
    private lateinit var authViewModel: AuthViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel=ViewModelProvider(this).get(AuthViewModel::class.java)

        //Al dar click
        binding.btnRegistrar.setOnClickListener {
            //Si la validacion es correcta o es verdadera
            if(validarDatos()){

                //Creando objeto cliente y capturando los datos del EditText o campo de texto
                val clientes = Clientes(
                    null,
                    binding.txtNombreRegister.text.toString(),
                    binding.txtApellidosRegister.text.toString(),
                    binding.txtCorreoRegister.text.toString(),
                    binding.txtFonoRegister.text.toString(),
                    binding.txtClaveRegister.text.toString(),
                    Rol(),
                    "0"
                )

                authViewModel.registrarUsuario(clientes)

            }
        }


        authViewModel.responseRegistro.observe(this,{ res ->
            if(res==1){
                Toast.makeText(applicationContext, "Registro con Exito", Toast.LENGTH_SHORT).show()
                //Retroceder a la actividad anterior
                finish()
            }
        })

    }

    fun validarDatos():Boolean{
        var validado=false
        if (validarNombre(binding.txtNombreRegister.text.toString()) &&
            validarApellidos(binding.txtApellidosRegister.text.toString()) &&
            validarCorreo(binding.txtCorreoRegister.text.toString()) &&
            validarFono(binding.txtFonoRegister.text.toString()) &&
            validarPassword(binding.txtClaveRegister.text.toString())){
            validado=true
        }
        return validado
    }

    //VAlidando campos
    fun validarNombre(nombre: String): Boolean {
        if (nombre.isEmpty()) {
            binding.layoutNombreRegister.error = "Ingrese Nombre"
            return false
        }
        if (nombre.length < 2) {
            binding.layoutNombreRegister.error = "Nombre no valido"
            return false
        }
        binding.layoutNombreRegister.error = ""
        return true
    }

    fun validarApellidos(apellido: String): Boolean {
        if (apellido.isEmpty()) {
            binding.layoutApellidosRegister.error = "Ingrese Apellidos"
            return false
        }
        if (apellido.length < 2) {
            binding.layoutApellidosRegister.error = "Apellido no valido"
            return false
        }
        binding.layoutApellidosRegister.error = ""
        return true
    }

    fun validarCorreo(correo: String): Boolean {
        if (correo.isEmpty()) {
            binding.layoutCorreoRegister.error = "Ingrese Correo"
            return false
        }
        /*if (!validarPatternCorreo(correo)) {
            binding.layoutCorreoRegister.error = "Correo no valido"
            return false
        }*/
        binding.layoutCorreoRegister.error = ""
        return true
    }

    fun validarFono(fono: String): Boolean {
        if (fono.isEmpty()) {
            binding.layoutFonoRegister.error = "Ingrese Telefono"
            return false
        }
        if (fono.length != 9) {
            binding.layoutFonoRegister.error = "Telefono no valido"
            return false
        }
        binding.layoutFonoRegister.error = ""
        return true
    }

    fun validarPassword(pass: String): Boolean {
        if (pass.isEmpty()) {
            binding.layoutClaveRegister.error = "Ingrese Contraseña"
            return false
        }
        if (pass.length < 8) {
            binding.layoutClaveRegister.error = "Contraseña corta, minimo 8 digitos"
            return false
        }
        binding.layoutClaveRegister.error = ""
        return true
    }

    //Validando el email, tipo de Patron Email
    fun validarPatternCorreo(correo: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(correo).matches()
    }


}