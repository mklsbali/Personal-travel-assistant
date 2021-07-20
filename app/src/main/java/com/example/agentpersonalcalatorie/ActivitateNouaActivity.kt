package com.example.agentpersonalcalatorie

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.icu.text.CaseMap
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.agentpersonalcalatorie.model.Activitate
import com.example.agentpersonalcalatorie.model.Activitati

class ActivitateNouaActivity : AppCompatActivity() {
    lateinit var data: String
    var id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitate_noua)

        id = (intent.getSerializableExtra("TRIP_ID") as? Int)!!
        data = (intent.getSerializableExtra("DATA") as? String).toString()

        val btn = findViewById<Button>(R.id.add)
        btn.setOnClickListener {
            add()
        }
    }

    fun checkInputs(title: String, hour: String,hour2: String, desc: String ): Boolean {
        var ok=true
        val titleError = findViewById<TextView>(R.id.errorTitle)
        val hourError = findViewById<TextView>(R.id.errorHour)
        val hour2Error = findViewById<TextView>(R.id.errorHour2)
        val descError = findViewById<TextView>(R.id.errorText)
        titleError.visibility=View.GONE
        hourError.visibility=View.GONE
        hour2Error.visibility=View.GONE
        descError.visibility=View.GONE

        if (title==""){
            ok=false
            titleError.visibility=View.VISIBLE
        }
        if (desc==""){
            ok=false
            descError.visibility=View.VISIBLE
        }
        if (hour=="" ||  hour.toInt()<0 || hour.toInt()>23){
            ok=false
            hourError.visibility=View.VISIBLE
        }

        if (hour2=="" || hour2.toInt()<0 || hour2.toInt()>59){
            ok=false
            hour2Error.visibility=View.VISIBLE
        }


        return ok

    }


    fun add() {
        val titleTView = findViewById<TextView>(R.id.titluInput)
        val hourTView = findViewById<TextView>(R.id.hourInput)
        val hour2TView = findViewById<TextView>(R.id.hourInput2)
        val descTView = findViewById<TextView>(R.id.descriereInput)

        val title = titleTView.text.toString()
        val hour = hourTView.text.toString()
        val hour2 = hour2TView.text.toString()
        val desc = descTView.text.toString()

        var isCheck=checkInputs(title,hour,hour2,desc)
        if (isCheck){
            Activitati.instance.addItem(title, "$hour:$hour2", desc, id, data)

            val str = data.split("/")
            val intent = Intent(this, TripPlanActivity::class.java)

            intent.putExtra("TRIP_ID", id)
            intent.putExtra("DAY", str[0].toInt())
            intent.putExtra("MONTH", str[1].toInt())
            intent.putExtra("YEAR", str[2].toInt())
            startActivity(intent)
        }

    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Parasiti aceasta pagina?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                val str = data.split("/")
                val intent = Intent(this, TripPlanActivity::class.java)
                intent.putExtra("TRIP_ID", id)
                intent.putExtra("DAY", str[0].toInt())
                intent.putExtra("MONTH", str[1].toInt())
                intent.putExtra("YEAR", str[2].toInt())
                startActivity(intent)
                finish()
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, witch ->

            })

        builder.create().show()
    }
}