package com.example.juego.menus

import android.content.Intent
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.juego.R
import com.example.juego.databinding.ActivityPantallaVictoriaBinding

class PantallaVictoria : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaVictoriaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaVictoriaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val musicVictory = MediaPlayer.create(this, R.raw.victory)
        musicVictory.isLooping = true
        musicVictory.start()

        val bundle = intent.extras
        val dato = bundle?.getString("Score")

        //  Toast.makeText(this, dato, Toast.LENGTH_LONG).show()
        val envio = findViewById<TextView>(R.id.txtPuntos)
        envio.text = dato.toString()

        binding.btnReinicio.setOnClickListener {
            musicVictory.stop()
            val intent = Intent(applicationContext, MenuPrincipal::class.java)

            startActivity(intent)

            finish()
        }
    }

    override fun onBackPressed() {

    }
}