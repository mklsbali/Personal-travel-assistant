package com.example.agentpersonalcalatorie

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.agentpersonalcalatorie.obiective_turistice_adapter.Sportive

class ObiectiveTuristice2Activity : AppCompatActivity() {
    lateinit var sportive: CheckBox
    lateinit var istorice: CheckBox
    lateinit var extreme: CheckBox
    lateinit var checkBoxes:ArrayList<CheckBox>
    lateinit var error: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.obiective_turistice2)
        setTitle("Obiective turistice")

        sportive=findViewById<CheckBox>(R.id.sportive)
        istorice=findViewById<CheckBox>(R.id.istorice)
        extreme=findViewById<CheckBox>(R.id.extreme)

        checkBoxes= arrayListOf<CheckBox>()
        checkBoxes.add(sportive)
        checkBoxes.add(istorice)
        checkBoxes.add(extreme)

        error=findViewById(R.id.OT2error)
        error.visibility= View.GONE

       /* val toast = Toast.makeText(applicationContext, sportive.text.toString()+" "+istorice.text.toString()
            , Toast.LENGTH_SHORT)
        toast.show()*/
        findViewById<Button>(R.id.inainteLaOTB).setOnClickListener {
            var anySelected:Boolean=false
            var selectedIndexes= arrayListOf<Boolean>()

            for (cb in checkBoxes) {
                selectedIndexes.add(cb.isChecked)
                if (cb.isChecked) {
                    anySelected=true

                }
            }
            if (!anySelected)
                error.visibility=View.VISIBLE
            else {
                error.visibility=View.GONE
                val intent= Intent(this, ObiectiveTuristice3Activity::class.java)
                intent.putExtra("Filters", selectedIndexes.toBooleanArray())
                startActivity(intent)
            }

        }

    }
}