package com.example.juego.dificultad

import android.content.Intent
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import com.example.juego.menus.PantallaDerrota
import com.example.juego.menus.PantallaVictoria
import com.example.juego.R
import com.example.juego.databinding.ActivityJuegoMedioBinding
import java.util.*

class JuegoMedio : AppCompatActivity() {

    //region Variables
// variables para los componentes de la vista
    private lateinit var binding: ActivityJuegoMedioBinding

    private var tablero = arrayOfNulls<ImageButton>(24)
    private var puntuacion = 0
    private var aciertos = 0
    private var movimientos = 0
    private var parejas = 12


    //imagenes
    private lateinit var imagenes: IntArray
    private var fondo = 0

    //variables del juego
    private var arrayDesordenado: ArrayList<Int>? = null
    private var primero: ImageButton? = null
    private var numeroPrimero = 0
    private var numeroSegundo: kotlin.Int = 0
    private var bloqueo = false
    private val handler = Handler()
    private var i = 0

    private lateinit var musicNazgul: MediaPlayer
//private val handler = Handler()

    //endregion Variables
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJuegoMedioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        musicNazgul = MediaPlayer.create(this, R.raw.nazgul)
        musicNazgul.start()
        init()

    }

    override fun onBackPressed() {

    }

    private fun loadTable() {
        var img00 = binding.boton00
        var img01 = binding.boton01
        var img02 = binding.boton02
        var img03 = binding.boton03
        var img04 = binding.boton04
        var img05 = binding.boton05
        var img06 = binding.boton06
        var img07 = binding.boton07
        var img08 = binding.boton08
        var img09 = binding.boton09
        var img10 = binding.boton10
        var img11 = binding.boton11
        var img12 = binding.boton12
        var img13 = binding.boton13
        var img14 = binding.boton14
        var img15 = binding.boton15
        var img16 = binding.boton16
        var img17 = binding.boton17
        var img18 = binding.boton18
        var img19 = binding.boton19
        var img20 = binding.boton20
        var img21 = binding.boton21
        var img22 = binding.boton22
        var img23 = binding.boton23



        tablero[0] = img00
        tablero[1] = img01
        tablero[2] = img02
        tablero[3] = img03
        tablero[4] = img04
        tablero[5] = img05
        tablero[6] = img06
        tablero[7] = img07
        tablero[8] = img08
        tablero[9] = img09
        tablero[10] = img10
        tablero[11] = img11
        tablero[12] = img12
        tablero[13] = img13
        tablero[14] = img14
        tablero[15] = img15
        tablero[16] = img16
        tablero[17] = img17
        tablero[18] = img18
        tablero[19] = img19
        tablero[20] = img20
        tablero[21] = img21
        tablero[22] = img22
        tablero[23] = img23


    }

    private fun timeBar() {
        binding.progressBar5!!.visibility = View.VISIBLE

        i = binding.progressBar5!!.progress

        Thread(Runnable {
            Thread.sleep(3000)
            // this loop will run until the value of i becomes 99
            while (i < 100) {
                i += 1
                Log.d("Hilo", "{$i}")
                // Update the progress bar and display the current value
                handler.post(Runnable {
                    binding.progressBar5!!.progress = i
                    // setting current progress to the textview
                    binding.textView!!.text = i.toString() + "/" + binding.progressBar5!!.max
                })
                try {
                    Thread.sleep(600)
                    // isActive=true
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            binding.progressBar5!!.visibility = View.INVISIBLE

            if (i == 100) {
                musicNazgul.stop()
                val intent = Intent(applicationContext, PantallaDerrota::class.java)
                startActivity(intent)
                finish()
            } else {
                musicNazgul.stop()
                val intent = Intent(applicationContext, PantallaVictoria::class.java)
                intent.putExtra("Score", "Tu puntuación fue de: $puntuacion puntos.\n ¡¡Victoria!!")
                startActivity(intent)
                finish()
            }

        }).start()

    }

    private fun loadText() {

        puntuacion = 0
        aciertos = 0
        movimientos = 0
        parejas = 12
        binding.textoPuntuacion!!.text = "Puntuacion: $puntuacion"
        binding.textoMovimientos!!.text = "Numero de movimientos: $movimientos"
        binding.textoParejas!!.text = "Numero parejas restantes: $parejas"
    }

    private fun loadImages() {
        imagenes = intArrayOf(
            R.drawable.la0,
            R.drawable.la1,
            R.drawable.la2,
            R.drawable.la3,
            R.drawable.la4,
            R.drawable.la5,
            R.drawable.la6,
            R.drawable.la7,
            R.drawable.la8,
            R.drawable.la9,
            R.drawable.la10,
            R.drawable.la11
        )
        fondo = R.drawable.fondo
    }

    private fun shuffle(longitud: Int): ArrayList<Int>? {
        val result = ArrayList<Int>()
        for (i in 0 until longitud * 2) {
            result.add(i % longitud)
        }
        Collections.shuffle(result)
        // System.out.println(Arrays.toString(result.toArray()));
        return result
    }

    private fun check(i: Int, imgb: ImageButton?) {

        if (primero == null) {
            primero = imgb
            primero!!.scaleType = ImageView.ScaleType.CENTER_CROP
            primero!!.setImageResource(imagenes[arrayDesordenado!![i]])
            primero!!.isEnabled = false
            numeroPrimero = arrayDesordenado!![i]
        } else {
            bloqueo = true
            imgb!!.scaleType = ImageView.ScaleType.CENTER_CROP
            imgb.setImageResource(imagenes[arrayDesordenado!![i]])
            imgb.isEnabled = false
            numeroSegundo = arrayDesordenado!![i]
            if (numeroPrimero == numeroSegundo) {
                primero = null
                bloqueo = false
                aciertos++
                puntuacion++
                movimientos++
                parejas--
                binding.textoPuntuacion!!.text = "Puntuación: $puntuacion"
                binding.textoMovimientos!!.text = "Numero de movimientos: $movimientos"
                binding.textoParejas!!.text = "Número parejas restantes: $parejas"
                if (aciertos == imagenes.size) {

                    this.i =
                        150  // le damos a i un numero mayor a 100 para que el hilo se detenga al ganar
                    binding.textView!!.text = "¡¡Victoria!!"
                }
            } else {
                handler.postDelayed({
                    primero!!.scaleType = ImageView.ScaleType.CENTER_CROP
                    primero!!.setImageResource(fondo)
                    primero!!.isEnabled = true
                    imgb.scaleType = ImageView.ScaleType.CENTER_CROP
                    imgb.setImageResource(fondo)
                    imgb.isEnabled = true
                    bloqueo = false
                    primero = null
                    puntuacion--
                    movimientos++
                    if (puntuacion < 0) puntuacion = 0
                    binding.textoPuntuacion!!.text = "Puntuación: $puntuacion"
                    binding.textoMovimientos!!.text = "Numero de movimientos: $movimientos"
                }, 500)
            }
        }
    }

    private fun init() {
        loadTable()
        loadText()
        loadImages()
        timeBar()
        arrayDesordenado = shuffle(imagenes.size)
        for (i in tablero.indices) {
            tablero[i]!!.scaleType = ImageView.ScaleType.CENTER_CROP
            tablero[i]!!.setImageResource(imagenes[arrayDesordenado!![i]])
            //tablero[i].setImageResource(fondo);
        }
        handler.postDelayed({
            for (i in tablero.indices) {
                tablero[i]!!.scaleType = ImageView.ScaleType.CENTER_CROP
                //tablero[i].setImageResource(imagenes[arrayDesordenado.get(i)]);
                tablero[i]!!.setImageResource(fondo)
            }
        }, 3000)
        for (i in tablero.indices) {
            tablero[i]!!.isEnabled = true
            tablero[i]!!.setOnClickListener { if (!bloqueo) check(i, tablero[i]) }
        }
    }
}
