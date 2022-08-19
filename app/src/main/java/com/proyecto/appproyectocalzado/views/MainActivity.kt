package com.proyecto.appproyectocalzado.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.proyecto.appproyectocalzado.R
import com.proyecto.appproyectocalzado.databinding.ActivityMainBinding
import com.proyecto.appproyectocalzado.views.fragments.ProductsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .add(R.id.container,ProductsFragment())
                .commit()
        }

    }
}