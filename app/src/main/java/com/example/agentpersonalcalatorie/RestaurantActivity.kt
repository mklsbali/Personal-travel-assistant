package com.example.agentpersonalcalatorie

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

class RestaurantActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant1)
        setTitle("Rezervare restaurant")
        var error=findViewById<TextView>(R.id.errorRestaurant1);
        error.visibility= View.GONE;
        var locationB=findViewById<Button>(R.id.locationRestaurantB);
        locationB.setOnClickListener({
            val restaurant=findViewById<EditText>(R.id.locationRestaurantText)
            if (restaurant.text.isEmpty())
            {
                error.visibility=View.VISIBLE;
            }
            else{
                error.visibility=View.GONE;
                restaurant2View();
            }
        })
    }
    private fun restaurant2View() {
        val intent = Intent(this, Restaurant2Activity::class.java)
        startActivity(intent)
    }

}