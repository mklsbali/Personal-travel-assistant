package com.example.agentpersonalcalatorie.restaurantadapter

import com.example.agentpersonalcalatorie.R

class Drinks {
    fun getDrinks(): ArrayList<MenuItem> {
        val foods=ArrayList<MenuItem>();
        foods.add(MenuItem("Bere Ciuc 500 ml", R.drawable.resdrink1,6,false))
        foods.add(MenuItem("Coca cola 330 ml", R.drawable.resdrink2,5,false))
        foods.add(MenuItem("Jack Daniel's 700 ml", R.drawable.resdrink3,70,false))
        foods.add(MenuItem("Vin Alturis 700 ml", R.drawable.resdrink4,50,false))

        return foods;
    }
}