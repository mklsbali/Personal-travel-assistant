package com.example.agentpersonalcalatorie.model

class Activitati {
    private var activitati = ArrayList<Activitate>()

    init {
        initActivitati();
    }

    private object INSTANCE {
        val INSTANCE = Activitati()
    }

    companion object {
        val instance: Activitati by lazy { INSTANCE.INSTANCE }
    }

    fun initActivitati(): ArrayList<Activitate> {
        activitati.add(Activitate(1,"Plecaare","12/07/2021","Plecare spre destinatie din aeroport ", "18:30",1))
        activitati.add(Activitate(2,"Plecare","16/07/2021","Plecare de la destinatie din aeroport", "18:30",1))
        activitati.add(Activitate(3,"Sosire","16/07/2021","Plecare de la destinatie din aeroport", "20:30",1))
        return activitati
    }

    fun getActivitatiForTrip(tripId: Int?, day: Int?, month:Int?, year: Int?): ArrayList<Activitate> {
        val newList=ArrayList<Activitate>()
        for(act in activitati)
            if(act.tripId==tripId){
                val str=act.date.split("/")
                if(str[0].toInt()==day && str[1].toInt()==month && str[2].toInt()==year)
                    newList.add(act)
            }

        return newList
    }

    fun addItem(title: String,hour: String,desc: String,id: Int,data: String){
        val count=activitati.count()
        activitati.add(Activitate(count,title,data,desc,hour,id))

    }


}