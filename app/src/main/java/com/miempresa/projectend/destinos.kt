package com.miempresa.projectend



import android.content.Intent
import android.media.ImageReader
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_destinos.*


class destinos : AppCompatActivity() {


    var destino = ""
    var latitud = ""
    var longitud = ""
    var info = ""
    var fotosr = R.drawable.segrampo




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinos)




        val recibidos = this.intent.extras
        if (recibidos != null) {
            destino = intent.extras!!.getString("destino")!!
            latitud = intent.extras!!.getString("latitud")!!
            longitud = intent.extras!!.getString("longitud")!!
            info = intent.extras!!.getString("info")!!
            fotosr = intent.extras!!.getInt("fotos")!!

        }

        lblDestino.setText(destino)
        lblCoordenadas.setText("$latitud , $longitud")
        lblInfo.setText(info)
        ima.setImageResource(fotosr)

        btnVolver.setOnClickListener(){
            volverLista(v = null)
        }
    }

    fun volverLista(v: View?) {
        val intent = Intent(this, ViewUsers::class.java)
        startActivity(intent)
    }

}