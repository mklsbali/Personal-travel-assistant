

import com.example.agentpersonalcalatorie.model.Suggestion


import com.example.agentpersonalcalatorie.R

class Suggestions {
    private var suggesting = ArrayList<Suggestion>()

    private object INSTANCE{
        val INSTANCE = Suggestions()
    }

    companion object{
        val instance:Suggestions by lazy{INSTANCE.INSTANCE}
    }

    init{
        initSugesti();
    }

    fun initSugesti(): ArrayList<Suggestion> {
        suggesting.add(Suggestion(1,1,"Geaca", R.drawable.sugestie_1,true))
        suggesting.add(Suggestion(2,2,"Geaca", R.drawable.sugestie_1,false))
        suggesting.add(Suggestion(3,1,"Pasaport", R.drawable.sugestie_2,false))
        suggesting.add(Suggestion(4,1,"Umbrela", R.drawable.sugestie_3,false))
        suggesting.add(Suggestion(5,2,"Pasaport", R.drawable.sugestie_2,false))
        suggesting.add(Suggestion(6,3,"Umbrela", R.drawable.sugestie_3,false))

        return suggesting
    }
    fun getSagestByID(id: Int?): List<Suggestion> {
        return suggesting.filter { it->it.idTrip==id }
    }

    fun take(id:Int){
        this.suggesting[id-1].take=!suggesting[id-1].take;
    }




}