package com.example.agentpersonalcalatorie

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.concurrent.schedule

class ObiectiveTuristice1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.obiective_turistice1)
        setTitle("Obiective turistice")

        var locationError=findViewById<TextView>(R.id.locationOTError)
        locationError.visibility= View.GONE
        findViewById<TextView>(R.id.textView18).visibility=View.GONE
        findViewById<EditText>(R.id.locationObiectiveTuristice).visibility=View.GONE
        findViewById<Button>(R.id.OTB1).visibility=View.GONE
        Timer("SettingUp", false).schedule(2000) {
            Handler(mainLooper).post{
                findViewById<TextView>(R.id.textView18).visibility=View.VISIBLE
                findViewById<EditText>(R.id.locationObiectiveTuristice).visibility=View.VISIBLE
                findViewById<Button>(R.id.OTB1).visibility=View.VISIBLE
                findViewById<ProgressBar>(R.id.progressBarOT1).visibility=View.GONE
            }
        }

        findViewById<Button>(R.id.OTB1).setOnClickListener {
            var locatieIntrodusa=findViewById<EditText>(R.id.locationObiectiveTuristice)


            var err:Boolean=false
            if (locatieIntrodusa.text.isEmpty()) {
                locationError.text="!! Acest camp este obligatoriu !!"
                locationError.visibility=View.VISIBLE
                err=true
            } else {
                locationError.visibility=View.GONE
                err=false
            }

            if (!err) {
                val intent= Intent(this, ObiectiveTuristice2Activity::class.java)
                startActivity(intent)
            }
        }
    }
}