package com.example.agentpersonalcalatorie



import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.agentpersonalcalatorie.R
import com.example.agentpersonalcalatorie.obiective_turistice_adapter.*


class ObiectiveTuristice3Activity : AppCompatActivity() {
    var objectivesAdapter: ObiectiveTuristiceAdapter? = null
    var dataSource:ArrayList<Objective>?=null
    lateinit var error:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.obiective_turistice3)
        setTitle("Obiective turistice")

        error=findViewById<TextView>(R.id.OT3error)
        error.visibility=View.GONE

        dataSource= arrayListOf<Objective>()
        var filters=intent.getBooleanArrayExtra("Filters")
        var sportive=Sportive().getSprotive()
        var istorice=Istorice().getIstorice()
        var extreme= Extreme().getExtreme()
        if (filters!![0]) {
            for (o in sportive)
                dataSource!!.add(o)
        }
        if (filters!![1]) {
            for (o in istorice)
                dataSource!!.add(o)
        }
        if (filters!![2]) {
            for (o in extreme)
                dataSource!!.add(o)
        }

        val listView = findViewById<ListView>(R.id.listViewOT)
        listView.visibility = View.VISIBLE


        objectivesAdapter = ObiectiveTuristiceAdapter(this@ObiectiveTuristice3Activity, dataSource as ArrayList<Objective>)
        listView.adapter = objectivesAdapter

        registerForContextMenu(listView)

        findViewById<Button>(R.id.maiDeparteLaHartaB).setOnClickListener {
            var selectedCnt:Int=0
            var anySelected:Boolean=false
           /* Toast.makeText(applicationContext, sportive.text.toString()+" "+istorice.text.toString()
                , Toast.LENGTH_SHORT).show()*/
            for (d in dataSource!!) {

                if (d.isSelected) {
                    anySelected=true
                    selectedCnt++
                }

            }
            if (!anySelected) {
                error.text="!! Nu ati ales nici-un obiectiv !!"
                error.visibility=View.VISIBLE
            }
            else if (selectedCnt>1) {
                error.text="!! Va rog sa alegeti un singur obiectiv !!"
                error.visibility=View.VISIBLE
            }
            else if(selectedCnt==1){
                error.visibility=View.GONE
                val intent= Intent(this, ObiectiveTuristice4Activity::class.java)
                startActivity(intent)
            }

        }
    }
}