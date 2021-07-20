package com.example.agentpersonalcalatorie

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import java.util.*

class Restaurant3Activity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    lateinit var dateTime: EditText
    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant3)
        setTitle("Detalii de rezervare")
        dateTime=findViewById(R.id.dataOra);
        findViewById<TextView>(R.id.nrOfPersonsError).visibility= View.GONE
        findViewById<TextView>(R.id.dataOraError).visibility= View.GONE
        var nextPageB=findViewById<Button>(R.id.inainteLaMeniuriB);

        dateTime.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, m: MotionEvent): Boolean {
                val calendar: Calendar = Calendar.getInstance()
                day = calendar.get(Calendar.DAY_OF_MONTH)
                month = calendar.get(Calendar.MONTH)
                year = calendar.get(Calendar.YEAR)
                val datePickerDialog =
                    DatePickerDialog(this@Restaurant3Activity, this@Restaurant3Activity, year, month,day)
                datePickerDialog.show()
                return true
            }
        })
        nextPageB.setOnClickListener({
            verifyDates()
        })

    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = day
        myYear = year
        myMonth = month+1
        val calendar: Calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(this@Restaurant3Activity, this@Restaurant3Activity, hour, minute,
            DateFormat.is24HourFormat(this))
        timePickerDialog.show()
    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        myHour = hourOfDay
        myMinute = minute

        var monthS:String=""
        var dayS:String=""
        var hourS:String=""
        var minuteS: String=""

        if (myMonth<10)
            monthS="0"+(myMonth).toString()
        else
            monthS=myMonth.toString()

        if (myDay<10)
            dayS="0"+(myDay).toString()
        else
            dayS=myDay.toString()

        if (myHour<10)
            hourS="0"+(myHour).toString()
        else
            hourS=myHour.toString()

        if (myMinute<10)
            minuteS="0"+(myMinute).toString()
        else
            minuteS=myMinute.toString()


        dateTime.setText(myYear.toString()+"/"+monthS+"/"+dayS+"   "+hourS+":"+minuteS)
    }
    private fun verifyDates(){
        var nrOfPersons=findViewById<EditText>(R.id.nrOfPersons)
        var err1:Boolean
        var err2:Boolean

        var nrOfPersonsError=findViewById<TextView>(R.id.nrOfPersonsError)
        var dateTimeError=findViewById<TextView>(R.id.dataOraError)



        if (nrOfPersons.text.isEmpty()){
            nrOfPersonsError.text="!! Acest camp este obligatoriu !!"
            nrOfPersonsError.visibility=View.VISIBLE
            err1=true;
        }
        else if (nrOfPersons.text.toString().toInt()<1 || nrOfPersons.text.toString().toInt()>100){
            nrOfPersonsError.text="!! Numarul persoanelor trebuie sa fie intre 1 si 100 !!"
            nrOfPersonsError.visibility=View.VISIBLE
            err1=true;
        }
        else {
            nrOfPersonsError.visibility=View.GONE
            err1=false;
        }
        var calendar: Calendar = Calendar.getInstance()
        var currentYear=calendar.get(Calendar.YEAR)

        if (dateTime.text.isEmpty())
        {
            dateTimeError.text="!! Acest camp este obligatoriu !!"
            dateTimeError.visibility=View.VISIBLE
            err2=true
        }
        else if (myYear<currentYear) {
            dateTimeError.text="!! Rezervarea trebuie sa fie in anul curent !!"
            dateTimeError.visibility=View.VISIBLE
            err2=true
        }
        else {
            dateTimeError.visibility=View.GONE
            err2=false
        }
        if (!err1 && !err2){
            val intent = Intent(this, Restaurant4Activity::class.java)
            startActivity(intent)
        }


    }

}