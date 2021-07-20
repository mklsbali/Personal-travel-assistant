package com.example.agentpersonalcalatorie.model

import android.icu.util.Calendar
import com.example.agentpersonalcalatorie.R

class MyTrips {
    private var trips = ArrayList<MyTrip>()
    init{
        initTrips();
    }

    private object INSTANCE{
        val INSTANCE = MyTrips()
    }

    companion object{
        val instance:MyTrips by lazy{INSTANCE.INSTANCE}
    }

    fun initTrips(): ArrayList<MyTrip> {
        trips.add(MyTrip(1,"\nBudapesta", R.drawable.trip_1,500,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).","12/07/2021-16/07/2021"));
        trips.add(MyTrip(2,"\nIslanda", R.drawable.trip_2,700,"The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.","12/07/2021-16/07/2021"));
        trips.add(MyTrip(3,"\nCluj-Napoca", R.drawable.trip_3,500,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.","15/07/2021-16/08/2021"));
        trips.add(MyTrip(4,"\nIslanda", R.drawable.trip_2,700,"The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.","12/07/2020-16/07/2020"));
        return trips;
    }
    fun getOTrips():ArrayList<MyTrip>{
        var newTrips=ArrayList<MyTrip>()
        for(trip in trips){
            var data=trip.date
            val str = data.split("-".toRegex()).toTypedArray()
            val dateStart=str[0]
            val str2=dateStart.split("/".toRegex()).toTypedArray()
            val day=str2[0].toInt()
            val mounth=str2[1].toInt()
            val year=str2[2].toInt()

            val c = Calendar.getInstance()

            val cyear = c.get(Calendar.YEAR)
            val cmonth = c.get(Calendar.MONTH)
            val cday = c.get(Calendar.DAY_OF_MONTH)
            if(year>cyear || (year==cyear && mounth>cmonth) || (year==cyear && mounth==cmonth && day>=cday)){
                newTrips.add(trip)
            }

        }
        return  newTrips
    }

    fun getAllTrips():ArrayList<MyTrip>{
        return trips
    }

    fun getTripId(id: Int?):MyTrip{
        return (trips.find{el->el.id ==id})!!
    }

    fun totalPrice(): Int {
        var totalPrice=0
        for(trip in trips){
            totalPrice+=trip.price
        }
        return totalPrice
    }

    fun getTripsByYear(year:Int):ArrayList<MyTrip>{
        var newTrips=ArrayList<MyTrip>()
        for(trip in trips){
            var data=trip.date
            val str = data.split("-".toRegex()).toTypedArray()
            val dateStart=str[0]
            val str2=dateStart.split("/".toRegex()).toTypedArray()
            val yearS=str2[2].toInt()
            if (yearS==year){
                newTrips.add(trip)
            }
        }
        return  newTrips
    }
}