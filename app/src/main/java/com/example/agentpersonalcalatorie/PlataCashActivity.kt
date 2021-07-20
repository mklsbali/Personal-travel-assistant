package com.example.agentpersonalcalatorie

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.transport.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class PlataCashActivity : AppCompatActivity() {
    var produse:ArrayList<String>?= arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plata_cash)
        produse=intent.getStringArrayListExtra("Produse")
        setTitle("Plata cu cash")
        findViewById<TextView>(R.id.textView13).visibility=View.GONE
        findViewById<TextView>(R.id.pretPlata).visibility=View.GONE
        findViewById<TextView>(R.id.textView14).visibility=View.GONE
        findViewById<Button>(R.id.plataCashMenuB).visibility=View.GONE
        findViewById<TextView>(R.id.textView16).visibility=View.GONE
        var produseView=findViewById<TextView>(R.id.produse)
        produseView.visibility=View.GONE

        var produseString:String=""
        for (p in produse!!)
        {
            produseString+="-"+p+"\n"
        }
        produseView.text=produseString

        var p:String=intent.getIntExtra("Pret",0).toString()+" lei"
        Timer("SettingUp", false).schedule(2000) {
            Handler(mainLooper).post{
                findViewById<ProgressBar>(R.id.progressBarCash).visibility=View.GONE
                findViewById<TextView>(R.id.processing).visibility=View.GONE

                findViewById<TextView>(R.id.textView13).visibility=View.VISIBLE
                var pret=findViewById<TextView>(R.id.pretPlata)
                pret.text=p
                pret.visibility=View.VISIBLE

                findViewById<TextView>(R.id.textView14).visibility=View.VISIBLE
                findViewById<Button>(R.id.plataCashMenuB).visibility=View.VISIBLE
                findViewById<TextView>(R.id.textView16).visibility=View.VISIBLE
                findViewById<TextView>(R.id.produse).visibility=View.VISIBLE
            }
        }



        findViewById<Button>(R.id.plataCashMenuB).setOnClickListener({
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        })
    }
}