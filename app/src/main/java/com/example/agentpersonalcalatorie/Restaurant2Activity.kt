package com.example.agentpersonalcalatorie

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Restaurant2Activity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant2)
        setTitle("Rezervare restaurant")
        fillDropdownWithValues()
        var restaurantB=findViewById<Button>(R.id.restaurantB);
        restaurantB.setOnClickListener({
            restaurant3View();
        })

    }
    private fun fillDropdownWithValues() {
        val spinner: Spinner = findViewById(R.id.restaurantList)
        // creez un ArrayAdapter folosind array-ul ala de string-uri din strings.xml si layout-u default pentru spinner (dropdown)
        ArrayAdapter.createFromResource(
            this,
            R.array.restaurantsArrayCluj,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // ce layout folosesc cand apare lista de variante
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // aplic adaptoru la elementu de tip spinner (dropdown)
            spinner.adapter = adapter
        }
    }
    private fun restaurant3View() {
        val intent = Intent(this, Restaurant3Activity::class.java)
        startActivity(intent)
    }
}