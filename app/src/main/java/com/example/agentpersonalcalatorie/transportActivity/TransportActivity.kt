package com.example.agentpersonalcalatorie.transportActivity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.agentpersonalcalatorie.MenuActivity
import com.example.agentpersonalcalatorie.R
import kotlinx.android.synthetic.main.transport.*
import java.util.*
import kotlin.concurrent.schedule


class TransportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transport)

        //fillDropdownWithValues();

        var button = findViewById<Button>(R.id.searchRouteBtn)
        button.setOnClickListener {
            searchRoute()
        }

        Timer("SettingUp", false).schedule(3000) {
            Handler(mainLooper).post {
                transportServiceLbl.visibility = View.VISIBLE
                startLbl.visibility = View.VISIBLE
                endLbl.visibility = View.VISIBLE
                startInput.visibility = View.VISIBLE
                endInput.visibility = View.VISIBLE
                startError.visibility = View.VISIBLE
                endError.visibility = View.VISIBLE
                transportError.visibility = View.VISIBLE
                searchRouteBtn.visibility = View.VISIBLE

                // resetez campurile de fiecare data cand se incarca pagina de transport
                startInput.clearFocus()
                endInput.clearFocus()
                startError.clearComposingText()
                endError.clearComposingText()
                transportError.clearComposingText()
            }
        }
    }

    private fun searchRoute() {
        val startInput = findViewById<EditText>(R.id.startInput).text.toString()
        val endInput = findViewById<EditText>(R.id.endInput).text.toString()

        val startError = findViewById<TextView>(R.id.startError)
        val endError = findViewById<TextView>(R.id.endError)

        // by default le fac vizibile, si daca totusi totul e ok, le fac dupaia GONE
        startError.visibility = View.VISIBLE
        endError.visibility = View.VISIBLE

        if (startInput == "") {
            startError.text = getString(R.string.introduceti_punctul_de_plecare)
        } else if (startInput.length < 3) {
            startError.text = getString(R.string.adresa_prea_scurta)
        } else {
            startError.text = ""
            startError.visibility = View.GONE
        }

        if (endInput == "") {
            endError.text = getString(R.string.introduceti_destinatia)
        } else if (endInput.length < 3) {
            endError.text = getString(R.string.adresa_prea_scurta)
        } else {
            endError.text = ""
            endError.visibility = View.GONE
        }

        // verific daca ii ascuns labelu de erori, ca daca e ascuns, inseamna ca e ok
        if (startError.visibility == View.GONE || endError.visibility == View.GONE) {

            // verific daca am ceva in label-urile de erori
            if (startError.text == "" && endError.text == "") {
                progressBarTransport.visibility = View.VISIBLE

                val intent = Intent(this, TransportOptionsActivity::class.java)
                intent.putExtra("startAddress", startInput)
                intent.putExtra("endAddress", endInput)

                startError.visibility = View.GONE
                endError.visibility = View.GONE

                // disable-uiesc toate butoanele si orice din pagina, cat timp se trimite cererea de cautare de transport (cat timp apare ProgressBar-ul pe ecran)
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )

                // las sa se invarta atata timp ProgressBar-u pana sa il fac invizibil si sa trec la urmatoarea activitate
                //Timer("SettingUp", false).schedule(0) {
                Handler(mainLooper).postDelayed({
                    progressBarTransport.visibility = View.GONE
                    startActivity(intent)
                }, 3000)
                //}
            } else {
                Log.d("CREATION", "\n\n\n nu e bine aici\n\n\n")
            }
        }
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Renuntati la cautare de rute?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                finish()
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            })
            .setNegativeButton(
                "No",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        builder.create().show()

    }

    /*private fun fillDropdownWithValues() {
        val spinner: Spinner = findViewById(R.id.gradesDropdown)
        // creez un ArrayAdapter folosind array-ul ala de string-uri din strings.xml si layout-u default pentru spinner (dropdown)
        ArrayAdapter.createFromResource(
            this,
            R.array.transportationWaysArray,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // ce layout folosesc cand apare lista de variante
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // aplic adaptoru la elementu de tip spinner (dropdown)
            spinner.adapter = adapter
        }
    }*/
}