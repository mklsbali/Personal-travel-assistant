package com.example.agentpersonalcalatorie.model

import com.example.agentpersonalcalatorie.R

class SharePersons {
    private var persons = ArrayList<Share>()

    fun fillPersonsCard(): ArrayList<Share> {
        persons.removeAll(persons)

        persons.add(Share(R.drawable.person_6, "Ana Costea", "anacostea@abc.com", "none", android.R.drawable.star_big_on))
        persons.add(Share(R.drawable.person_3, "Pop Crina", "popcrina@abc.com", "none", android.R.drawable.star_big_on))
        persons.add(Share(R.drawable.person_4, "Marinela Dan", "email@email.com", "none", android.R.drawable.star_big_on))
        persons.add(Share(R.drawable.person_1, "Ion Pop", "marineladan@abc.com", "none", android.R.drawable.star_big_on))
        persons.add(Share(R.drawable.person_5, "Diana Muresan", "dianamuresan@abc.com", "none", android.R.drawable.star_big_on))
        persons.add(Share(R.drawable.person_6, "Stanca Ionescu", "stancaionescu@abc.com", "none", android.R.drawable.star_big_on))
        persons.add(Share(R.drawable.person_2, "Cristian Popescu", "cristianpopescu@abc.com", "none", android.R.drawable.star_big_on))

        return persons
    }
    init {
        fillPersonsCard()
    }

    companion object {
        val instance: SharePersons by lazy { SharePersons() }
    }
}