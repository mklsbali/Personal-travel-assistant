package com.example.agentpersonalcalatorie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Plata1Activity : AppCompatActivity(){

    var totalPrice:Int=0
    var produse:ArrayList<String>?= arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.plata1)
        totalPrice=intent.getIntExtra("Pret",0)
        produse=intent.getStringArrayListExtra("Produse")
        setTitle("Modalitatea de plata")

        findViewById<Button>(R.id.cashB).setOnClickListener({
            cash()
        })
        findViewById<Button>(R.id.cardB).setOnClickListener({
            card()
        })
    }
    private fun cash(){
        val intent = Intent(this, PlataCashActivity::class.java)
        intent.putExtra("Pret", totalPrice)
        intent.putExtra("Produse",produse)
        startActivity(intent)
    }
    private fun card(){
        val intent = Intent(this, PlataCard1Activity::class.java)
        intent.putExtra("Pret", totalPrice)
        intent.putExtra("Produse",produse)
        startActivity(intent)
    }
}