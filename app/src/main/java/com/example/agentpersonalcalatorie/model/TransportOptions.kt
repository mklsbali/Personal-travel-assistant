package com.example.agentpersonalcalatorie.model

import com.example.agentpersonalcalatorie.R

class TransportOptions {
    private var options = ArrayList<TransportOption>()

    fun fillRoutesWithOptions(): ArrayList<TransportOption> {
        options.removeAll(options)

        options.add(TransportOption("CAR",  "35 min ", 27.0, "Complicat, trafic        ", "",     0.0,  0.0, "",        R.drawable.car_1))
        options.add(TransportOption("CAR",  "24 min ", 12.0, "Usor                     ", "",     0.0,  0.0, "",        R.drawable.car_2))
        options.add(TransportOption("BUS",  "10 min  ", 3.5, "Uso de urmat             ", "24B",  0.0,  2.5, "",        R.drawable.bus_1))
        options.add(TransportOption("BUS",  "17 min  ", 4.2, "Linie unica              ", "25",   0.0,  5.0, "",        R.drawable.bus_2))
        options.add(TransportOption("BUS",  "26 min  ", 8.0, "Schimbare de linii  ", "48 & 35",   0.0,  5.0, "",        R.drawable.bus_3))
        options.add(TransportOption("WALK", "19 min  ", 1.8, "Fara panta               ", "",    32.0,  0.0, "",        R.drawable.walk_1))
        options.add(TransportOption("WALK", "43 min  ", 4.8, "Panta cu urcare grea     ", "",   164.0,  0.0, "",        R.drawable.walk_2))
        options.add(TransportOption("WALK", "22 min  ", 2.0, "Panta cu urcare usoara   ", "",    64.0,  0.0, "",        R.drawable.walk_3))
        options.add(TransportOption("WALK", "67 min  ", 5.6, "Panta cu urcare usoara  ", "",    230.0,  0.0, "",        R.drawable.walk_4))
        options.add(TransportOption("TAXI", "11 min  ", 3.1, "Descriere optiune curenta", "",     0.0, 32.0, "CJ38DCC", R.drawable.taxi_1))
        options.add(TransportOption("TAXI", "22 min  ", 5.4, "Descriere optiune curenta", "",     0.0, 25.3, "CJ01ABC", R.drawable.taxi_2))

        return options
    }

    init {
        fillRoutesWithOptions()
    }

    companion object {
        val instance: TransportOptions by lazy { TransportOptions() }
    }

    fun getAllSpecificOptions(transportType: String): List<TransportOption> {
        return options.filter {
                option -> option.categorie.compareTo(transportType) == 0
        }
    }
}