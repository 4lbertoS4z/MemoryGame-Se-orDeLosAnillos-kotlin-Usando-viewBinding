package com.example.juego.menus

import android.content.Intent
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.juego.R
import com.example.juego.dificultad.SelectorDificultad
import com.example.juego.databinding.ActivityMenuPrincipalBinding

class MenuPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityMenuPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val musicOpening = MediaPlayer.create(this, R.raw.opening)
        musicOpening.isLooping = true
        musicOpening.start()

        binding.btnJugar.setOnClickListener {
            musicOpening.stop()
            val intent = Intent(applicationContext, SelectorDificultad::class.java)

            startActivity(intent)
            finish()
        }
    }
}