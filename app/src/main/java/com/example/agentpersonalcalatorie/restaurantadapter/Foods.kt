package com.example.agentpersonalcalatorie.restaurantadapter

import android.view.Menu
import com.example.agentpersonalcalatorie.R

class Foods {
    fun getFoods(): ArrayList<MenuItem> {
        val foods=ArrayList<MenuItem>();
        foods.add(MenuItem("Ceafa de porc cu cartofi prajiti", R.drawable.resfood1,21,false))
        foods.add(MenuItem("Piept de pui la gratar cu legime", R.drawable.resfood2,20,false))
        foods.add(MenuItem("Mici cu mustar", R.drawable.resfood3,15,false))
        foods.add(MenuItem("Salata franceza", R.drawable.resfood4,18,false))

        return foods;
    }
}