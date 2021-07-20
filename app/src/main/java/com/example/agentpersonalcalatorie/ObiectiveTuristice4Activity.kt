package com.example.agentpersonalcalatorie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.agentpersonalcalatorie.transportActivity.TransportActivity
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

internal class ObiectiveTuristice4Activity : AppCompatActivity(), OnMapReadyCallback{
    lateinit var map: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.obiective_turistice4)
        setTitle("Map")

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        findViewById<Button>(R.id.inapoiOT4Btn).setOnClickListener{
            startActivity(Intent(this, MenuActivity::class.java))
        }
        findViewById<Button>(R.id.mijlocTrOT4Btn).setOnClickListener{
            startActivity(Intent(this, TransportActivity::class.java))
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val dracula_castle = LatLng(45.51642078448871, 25.363344232480546)
        map.addMarker(MarkerOptions()
            .position(dracula_castle)
            .title("Drakula's castle"))
        map.moveCamera(CameraUpdateFactory.newLatLng(dracula_castle))
    }


}