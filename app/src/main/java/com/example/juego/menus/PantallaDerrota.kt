package com.example.juego.menus

import android.content.Intent
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.juego.R
import com.example.juego.databinding.ActivityPantallaDerrotaBinding

class PantallaDerrota : AppCompatActivity() {
    private lateinit var binding: ActivityPantallaDerrotaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPantallaDerrotaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val musicDefeat = MediaPlayer.create(this, R.raw.derrota)
        musicDefeat.isLooping = true
        musicDefeat.start()

        binding.btnReinicio.setOnClickListener {
            musicDefeat.stop()
            val intent = Intent(applicationContext, MenuPrincipal::class.java)

            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {

    }
}