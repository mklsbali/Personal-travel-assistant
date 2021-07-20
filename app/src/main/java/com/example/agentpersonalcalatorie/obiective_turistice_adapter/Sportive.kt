package com.example.agentpersonalcalatorie.obiective_turistice_adapter

import com.example.agentpersonalcalatorie.R

class Sportive {
    fun getSprotive(): ArrayList<Objective> {
        val sportive=ArrayList<Objective>();
        sportive.add(Objective("Baza sportiva Ghiorgheni", R.drawable.os1,"Cel mai modern complex sportiv din Municipiul Cluj Napoca", false))
        sportive.add(Objective("Federatia Romana De Fotbal baza Mogosoaia", R.drawable.os2,"Federatia Romana De Fotbal baza Mogosoaia, Mogosoaia, Romania.", false))

        return sportive;
    }
}