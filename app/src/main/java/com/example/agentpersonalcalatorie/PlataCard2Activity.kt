package com.example.agentpersonalcalatorie

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import java.util.*
import kotlin.concurrent.schedule

class PlataCard2Activity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plata_card2)
        setTitle("Plata cu card")
        findViewById<TextView>(R.id.succes).visibility=View.GONE
        findViewById<TextView>(R.id.multumim).visibility=View.GONE
        var button=findViewById<Button>(R.id.inapoiPlataCardMeniuB)
        button.visibility=View.GONE

        Timer("SettingUp", false).schedule(2000) {
            Handler(mainLooper).post{
                findViewById<TextView>(R.id.succes).visibility=View.VISIBLE
                findViewById<TextView>(R.id.multumim).visibility=View.VISIBLE
                button.visibility=View.VISIBLE
                findViewById<ProgressBar>(R.id.progressBarPlataCard2).visibility=View.GONE
                findViewById<TextView>(R.id.progressTextPlataCard2).visibility=View.GONE
            }
        }
        button.setOnClickListener({
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        })
    }
}