package com.example.agentpersonalcalatorie.obiective_turistice_adapter

import com.example.agentpersonalcalatorie.R

class Istorice {
    fun getIstorice(): ArrayList<Objective> {
        val istorice=ArrayList<Objective>();
        istorice.add(Objective("Casa lui Mihai Eminescu", R.drawable.oi1,"Casa memorială Mihai Eminescu de la Ipotești este un muzeu memorial amenajat în casa în care a trăit poetul Mihai Eminescu (1850-1889) în satul Ipotești din județul Botoșani.", false))
        istorice.add(Objective("Castelul Peles", R.drawable.oi2,"Castelul Peleș a fost construit la inițiativa primului Rege al României, Carol I, în afara perimetrului comunei Podul Neagului, localitate cu o suprafață de 24 de km în anul 1874, an în care, din inițiativa suveranului, comuna primește numele de Sinaia.", false))
        istorice.add(Objective("Castelul Bran", R.drawable.oi3,"Castelul Bran (în germană Törzburg, în maghiară Törcsvár) este un monument istoric și arhitectonic situat în Pasul Bran-Rucăr, la 30 de kilometri de Brașov.", false))

        return istorice;
    }
}