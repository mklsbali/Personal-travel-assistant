package com.example.agentpersonalcalatorie.shareActivity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.agentpersonalcalatorie.MenuActivity
import com.example.agentpersonalcalatorie.R
import com.example.agentpersonalcalatorie.transportActivity.TransportActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.transport_route_end.*
import java.util.*
import kotlin.concurrent.schedule

class ShareShowMap: AppCompatActivity(), OnMapReadyCallback {
    lateinit var map: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.share_map)
        setTitle("Map")

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapShare) as SupportMapFragment
        mapFragment.getMapAsync(this)

        var shareLocatieCuPrieteni = findViewById<Button>(R.id.shareLocatieCuPrieteni)
        shareLocatieCuPrieteni.setOnClickListener {
            val intent = Intent(this, ShareActivity::class.java)
            startActivity(intent)
        }

        Timer("SettingUp", false).schedule(2000) {
            Handler(mainLooper).post {
                shareLocatieCuPrieteni.bringToFront()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val dracula_castle = LatLng(45.51642078448871, 25.363344232480546)
        map.addMarker(
            MarkerOptions()
                .position(dracula_castle)
                .title("Dracula's castle")
        )
        map.moveCamera(CameraUpdateFactory.newLatLng(dracula_castle))
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Renuntati la share locatie?")
            .setPositiveButton("Da", DialogInterface.OnClickListener { dialog, id ->
                finish()
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            })
            .setNegativeButton(
                "Nu",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        builder.create().show()
    }
}
