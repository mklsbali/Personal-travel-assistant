package com.example.agentpersonalcalatorie

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.agentpersonalcalatorie.model.MyTrip

class TripDetailsActivity : AppCompatActivity() {

    var myTrip: MyTrip? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trip_details)

        myTrip = intent.getSerializableExtra("MyTrip") as MyTrip
        findViewById<TextView>(R.id.title).text = myTrip!!.title
        findViewById<TextView>(R.id.price).text = myTrip!!.price.toString() + "EUR"
        findViewById<TextView>(R.id.description).text = myTrip!!.description
        findViewById<ImageView>(R.id.imageView).setImageResource(myTrip!!.image)
        findViewById<TextView>(R.id.period).text = myTrip!!.date
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sugestiID) {
            val intent = Intent(this, SuggestionsActivity::class.java)
            intent.putExtra("TRIP_ID", myTrip?.id)
            startActivity(intent)
        } else if (item.itemId == R.id.planID) {
            val intent = Intent(this, CalendarActivity::class.java)
            intent.putExtra("TRIP_ID", myTrip?.id)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item);

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.trip_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        val intent = Intent(this, MyTripsActivity::class.java)
        startActivity(intent)
    }
}