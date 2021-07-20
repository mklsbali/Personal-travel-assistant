package com.example.agentpersonalcalatorie

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.agentpersonalcalatorie.adapter.MyTripAdapter
import com.example.agentpersonalcalatorie.model.MyTrips
import java.util.*
import kotlin.concurrent.schedule

class IstoricActivity  : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.istoric_start)

        Timer("SettingUp", false).schedule(1500) {
            Handler(mainLooper).post {
                val progressBar = findViewById<ProgressBar>(R.id.progressBar3)
                val text1=findViewById<TextView>(R.id.textView2)
                val text2=findViewById<TextView>(R.id.textView3)
                val btn=findViewById<Button>(R.id.allBtn)

                progressBar.visibility = View.GONE
                text1.visibility=View.VISIBLE
                text2.visibility=View.VISIBLE
                btn.visibility=View.VISIBLE

                text2.text= MyTrips.instance.totalPrice().toString()+"EUR"
                btn.setOnClickListener {
                    tripsIstoricView()
                }

            }
        }


    }

    private fun tripsIstoricView(){
        val intent = Intent(this, IstoricTripsActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Parasiti aceasta pagina?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, witch ->

            })
        builder.create().show()
    }


}