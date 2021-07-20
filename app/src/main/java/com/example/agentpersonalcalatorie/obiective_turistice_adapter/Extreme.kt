package com.example.agentpersonalcalatorie.obiective_turistice_adapter

import com.example.agentpersonalcalatorie.R
import com.example.agentpersonalcalatorie.restaurantadapter.MenuItem

class Extreme {
    fun getExtreme(): ArrayList<Objective> {
        val extreme=ArrayList<Objective>();

        extreme.add(Objective("Muntii Fagaras",R.drawable.oe1,"Munții Făgăraș reprezintă un masiv muntos care face parte din Carpații Meridionali, și în care se află cel mai înalt vârf montan din România, vârful Moldoveanu, cu altitudinea de 2.544 de metri.",false))
        extreme.add(Objective("Lacul Sfantu Ana",R.drawable.oe2,"Lacul Sfânta Ana (în maghiară Szent Anna-tó) este singurul lac vulcanic din România. Este situat în masivul Ciomatu din județul Harghita, în stânga râului Olt, în apropiere de stațiunea Tușnad.",false))
        extreme.add(Objective("Plaja Mamaia",R.drawable.oe3,"Plaja este o porțiune de teren, acoperită cu nisip fin, de la baza unei faleze ori de pe panta lină dinspre mare a unui cordon litoral sau al unui curs de apă, acoperită cu nisip fin.",false))
        return extreme
    }
}