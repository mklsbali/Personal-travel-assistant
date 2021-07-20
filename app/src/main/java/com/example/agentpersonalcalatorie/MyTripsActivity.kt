package com.example.agentpersonalcalatorie

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.agentpersonalcalatorie.R
import com.example.agentpersonalcalatorie.adapter.MyTripAdapter
import com.example.agentpersonalcalatorie.model.MyTrip
import com.example.agentpersonalcalatorie.model.MyTrips
import java.util.*
import kotlin.concurrent.schedule

class MyTripsActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    var myTripAdapter: MyTripAdapter? = null
    val REQUEST_CODE = 1001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_trip_list)

        Timer("SettingUp", false).schedule(3000) {
            Handler(mainLooper).post {
                val progressBar = findViewById<ProgressBar>(R.id.progressBar)
                progressBar.visibility = View.GONE
                val listView = findViewById<ListView>(R.id.listView)
                listView.visibility = View.VISIBLE

                myTripAdapter = MyTripAdapter(this@MyTripsActivity, MyTrips.instance.getOTrips())
                listView.adapter = myTripAdapter
                registerForContextMenu(listView)

                listView.setOnItemClickListener(this@MyTripsActivity)
            }
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val myTrip = myTripAdapter!!.getItem(position) as MyTrip
        val intent = Intent(this, TripDetailsActivity::class.java)
        intent.putExtra("MyTrip", myTrip)
        startActivityForResult(intent, REQUEST_CODE)
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