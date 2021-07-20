package com.example.agentpersonalcalatorie

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agentpersonalcalatorie.adapter.ActivitateAdapter
import com.example.agentpersonalcalatorie.model.Activitate
import com.example.agentpersonalcalatorie.model.Activitati
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class TripPlanActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var id: Int = 0
    private lateinit var adapter: ActivitateAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitate_list)
        Timer("SettingUp", false).schedule(1500) {
            Handler(mainLooper).post {
                id = (intent.getSerializableExtra("TRIP_ID") as? Int)!!
                val day = intent.getSerializableExtra("DAY") as? Int
                val month = intent.getSerializableExtra("MONTH") as? Int
                val year = intent.getSerializableExtra("YEAR") as? Int

                val progressBar=findViewById<ProgressBar>(R.id.progressBar5)
                val recyclerView = findViewById<RecyclerView>(R.id.recycler)
                val btn = findViewById<Button>(R.id.add)
                val textView=findViewById<TextView>(R.id.textView4)
                progressBar.visibility=View.GONE
                recyclerView.visibility=View.VISIBLE
                btn.visibility=View.VISIBLE
                textView.visibility=View.VISIBLE

                linearLayoutManager = LinearLayoutManager(this@TripPlanActivity)
                adapter = ActivitateAdapter(this@TripPlanActivity, ArrayList())

                recyclerView.layoutManager = linearLayoutManager
                recyclerView.adapter = adapter
                var todayAct = Activitati.instance.getActivitatiForTrip(id, day, month, year)
                for (t in todayAct)
                    adapter.addItem(t)

                btn.setOnClickListener {
                    clickBtn(day.toString(),month.toString(),year.toString())

                }

            }
        }

    }

    fun clickBtn(day: String,  month:String, year:String){
        val intent = Intent(this, ActivitateNouaActivity::class.java)
        intent.putExtra("TRIP_ID", id)
        intent.putExtra(
            "DATA",
            day.toString() + '/' + month.toString() + '/' + year.toString()
        )
        startActivity(intent)
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Parasiti aceasta pagina?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                val intent = Intent(this, CalendarActivity::class.java)
                intent.putExtra("TRIP_ID", id)
                startActivity(intent)
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, witch ->

            })

        builder.create().show()
    }
}