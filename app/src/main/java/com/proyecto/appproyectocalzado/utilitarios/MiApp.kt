package com.proyecto.appproyectocalzado.utilitarios

import android.app.Application
import android.content.Context
import android.os.StrictMode
import com.proyecto.appproyectocalzado.BuildConfig

class MiApp : Application() {

    //Obtener instancia de la aplicacion/contexto
    init { INSTANCE = this }

    //Agrupa todos las variables y métodos que están definidos como
    //estáticos
    companion object {
        lateinit var INSTANCE: MiApp
            private set

        val applicationContext: Context get() { return INSTANCE.applicationContext }
    }
}