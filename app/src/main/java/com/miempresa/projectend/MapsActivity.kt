package com.miempresa.projectend


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView

import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.miempresa.projectend.databinding.ActivityMapsBinding
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.activity_view_users.*
import org.json.JSONException
import java.io.IOException

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, AdapterView.OnItemSelectedListener {



    private val Plaza = LatLng(-16.3988032, -71.5390943)
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    var nombre = ""
    var marcadorDestino: Marker? = null
    var coordenada = LatLng(0.0, 0.0)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)



        val recibidos = this.intent.extras
        if (recibidos != null) {
            nombre = intent.extras!!.getString("nombre")!!
        }







    }

    /*-------------------------------------------------------------------*/

    fun Apifast() {
        //ListaDistridos.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        //ListaDistridos.layoutManager = LinearLayoutManager(this)
        AsyncTask.execute {
            val queue = Volley.newRequestQueue(applicationContext)
            val url = "https://apicombislogin.onrender.com/717ab3b1503b549a393b14f0740a1312c90c29da/api/usuarios/"
            val stringRequest = JsonArrayRequest(url,
                { response ->
                    try {
                        Toast.makeText(
                            applicationContext,
                            "Se obtubo i formacion de la Apifast",
                            Toast.LENGTH_LONG
                        ).show()



                    } catch (e: JSONException) {
                        Toast.makeText(
                            applicationContext,
                            "Error al obtener los datos",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } , {
                    Toast.makeText(
                        applicationContext,
                        "Verifique que esta conectado a internet",
                        Toast.LENGTH_LONG
                    ).show()
                })
            queue.add(stringRequest)
        }
    }




    /*-------------------------------------------------------------------*/







    @RequiresApi(Build.VERSION_CODES.M)
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.getUiSettings().setAllGesturesEnabled(true)
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.getUiSettings().setCompassEnabled(true)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 123);
        }
        when (nombre) {
            "segrampo" -> {
                coordenada = LatLng(-16.41484, -71.5049654)
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Enpresa de transporte: $nombre")
                )
            }
            "cotum" -> {
                coordenada = LatLng(-16.387011, -71.574635)
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Enpresa de transporte: $nombre")
                )
            }
            "cotap" -> {
                coordenada = LatLng(-16.4484467, -71.5527598)
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Enpresa de transporte: $nombre")
                )
            }
            "characato" -> {
                coordenada = LatLng(-16.490144, -71.496088)
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Enpresa de transporte: $nombre")
                )
            }

            else -> {
                Toast.makeText(this, "No se encontro destino tal Empresa", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        // Cámara
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenada, 15f))
        // Eventos
        mMap.setOnMarkerClickListener(this)
    }
    fun moverCamara(view: View?){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Plaza, 18f))
    }
    fun agregarMarcador(view:View?){
        mMap.addMarker(
            MarkerOptions().position(
                LatLng(
                    mMap.cameraPosition.target.latitude,
                    mMap.cameraPosition.target.longitude
                )
            )
                .title("Mi Ubicacion")
        )
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        if (p0!!.equals(marcadorDestino)) {

            var mensajex = ""

            val fotos1= arrayOf(
                R.drawable.segrampo,
                R.drawable.cotum,
                R.drawable.cotap,
                R.drawable.characato

            )
            var fotos = fotos1.get(0)
            when (nombre) {
                "segrampo" -> {
                    mensajex = "SEGRAMPO\n" +
                        "REPRESENTANTE: Del Carpio Kuong D Cruz Gladis Ludgarda\n" +
                            "\n" +
                            "DIRECCION DE LA EMPRESA: 27 de Julio 107 los Olivos-Jose Luis Bustamante y Rivero\n" +
                            "\n" +
                            "\n" +
                            "Ruta A 37A: Av.Kennedy-Umacollo-Jose Luis B. y Rivero-Cercado y Viceversa"
                    fotos = fotos1.get(0)
                }
                "cotum" -> {
                    mensajex = "COTUM\n" +
                            "REPRESENTANTE:Coaquira Mamani Susana Sebastiana\n" +
                            "\n" +
                            "DIRECCION DE LA EMPRESA:Av.Restauracion Manz.D-29, Lote 18(RG 080-2007)-Cerro Colorado\n" +
                            "\n" +
                            "\n" +
                            "Ruta C 028: Monterrey-Cercado-Pachacutec y Envolvente"
                    fotos = fotos1.get(1)
                }
                "cotap" -> {
                    mensajex = "COTAP\n" +
                            "REPRESENTANTE: Lopez Portilla Miguel Angel\n" +
                            "\n" +
                            "DIRECCION DE LA EMPRESA:Callelos Angeles 105 PJ Miguel Grau-Paucarpata\n" +
                            "\n" +
                            "\n" +
                            "Ruta C 036A: Miguel Grau-Cercado-Terminal terrestre y Viceversa"
                    fotos = fotos1.get(2)
                }
                "characato" -> {
                    mensajex = "Characato\n" +
                            "REPRESENTANTE: Lopez Portilla Wilber Sabino\n" +
                            "\n" +
                            "DIRECCION DE LA EMPRESA:Calle Moquegua Nro 110-Characato\n" +
                            "\n" +
                            "\n" +
                            "Ruta C 64A: Mollebaya-Characato-Cercado y Viceversa"
                    fotos = fotos1.get(3)


            }}



            val intent = Intent(this, destinos::class.java)
            intent.putExtra("destino", nombre)
            intent.putExtra("latitud", p0.getPosition().latitude.toString() + "")
            intent.putExtra("longitud", p0.getPosition().longitude.toString() + "")
            intent.putExtra("info", mensajex)
            intent.putExtra("fotos", fotos)

            startActivity(intent)
            return true

        }
        return false
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}