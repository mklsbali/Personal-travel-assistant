package com.example.agentpersonalcalatorie

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agentpersonalcalatorie.adapter.MyTripAdapter
import com.example.agentpersonalcalatorie.model.MyTrip
import com.example.agentpersonalcalatorie.model.MyTrips


class CalendarActivity : AppCompatActivity() {

    var id:Int=0
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar)

        id = (intent.getSerializableExtra("TRIP_ID") as? Int)!!

        if (id != null) {
            calendarStar(id)
        }

        val calendarView = findViewById<CalendarView>(R.id.calendarView)

        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
            val msg = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year
            Toast.makeText(this@CalendarActivity, msg, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TripPlanActivity::class.java)
            intent.putExtra("TRIP_ID", id)
            intent.putExtra("DAY", dayOfMonth)
            intent.putExtra("MONTH", month + 1)
            intent.putExtra("YEAR", year)
            startActivity(intent)
        }

    }


    fun calendarStar(id: Int){
        val trip= MyTrips.instance.getTripId(id)

        val str = trip.date.split("-".toRegex()).toTypedArray()
        val dateStartS= str[0]
        val dateEndS=str[1]
        val parts = dateStartS.split("/")

        val day=parts[0].toInt()
        val month=parts[1].toInt()
        val year=parts[2].toInt()

        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        val milliTime = calendar.timeInMillis

        val parts2 = dateEndS.split("/")
        val day2=parts2[0].toInt()
        val month2=parts2[1].toInt()
        val year2=parts2[2].toInt()


        calendar.set(Calendar.YEAR, year2)
        calendar.set(Calendar.MONTH, month2 - 1)
        calendar.set(Calendar.DAY_OF_MONTH, day2)
        val milliTime2 = calendar.timeInMillis

        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        calendarView.minDate=milliTime
        calendarView.maxDate=milliTime2
    }

    override fun onBackPressed() {
        val myTrip = MyTrips.instance.getTripId(id)
        val intent = Intent(this, TripDetailsActivity::class.java)
        intent.putExtra("MyTrip", myTrip)
        startActivityForResult(intent, 2001)
    }


}