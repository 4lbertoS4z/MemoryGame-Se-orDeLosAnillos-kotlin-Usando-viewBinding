package com.example.juego.dificultad

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.juego.databinding.ActivitySelectorDificultadBinding

class SelectorDificultad : AppCompatActivity() {

    private lateinit var binding: ActivitySelectorDificultadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectorDificultadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        binding.btnFacil.setOnClickListener {

            val intent = Intent(applicationContext, Juego::class.java)

            startActivity(intent)
            finish()
        }

        binding.btnMedio.setOnClickListener {

            val intent = Intent(applicationContext, JuegoMedio::class.java)

            startActivity(intent)
            finish()
        }

        binding.btnDificil.setOnClickListener {

            val intent = Intent(applicationContext, JuegoDificil::class.java)

            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {

    }
}