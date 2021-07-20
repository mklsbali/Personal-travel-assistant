package com.example.agentpersonalcalatorie

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat.postDelayed
import kotlinx.android.synthetic.main.feedback.*
import java.util.*
import kotlin.concurrent.schedule


class FeedbackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feedback)

        fillDropdownWithValues()

        /*var button = findViewById<Button>(R.id.sendFeedbackBtn)
        button.setOnClickListener {
            sendFeedback()
        }*/

        Timer("SettingUp", false).schedule(3000) {
            Handler(mainLooper).post {
                feedbackServiceLbl.visibility = View.VISIBLE
                gradeLbl.visibility = View.VISIBLE
                gradesDropdown.visibility = View.VISIBLE
                opinionLbl.visibility = View.VISIBLE
                sendFeedbackBtn.visibility = View.VISIBLE
                opinionInput.visibility = View.VISIBLE
                feedbackError.visibility = View.VISIBLE
                gradeError.visibility = View.VISIBLE
                inputError.visibility = View.VISIBLE

                // resetez campurile de fiecare data cand se incarca pagina de feedback
                gradesDropdown.setSelection(0)
                opinionInput.clearFocus()
                gradeError.clearComposingText()
                inputError.clearComposingText()
            }
        }
    }


    private fun sendFeedback() {
        var feedbackInput = findViewById<EditText>(R.id.opinionInput).text.toString()

        var inputError = findViewById<TextView>(R.id.inputError)
        var feedbackError = findViewById<TextView>(R.id.feedbackError)

        // by default le fac vizibile, si daca totusi totul e ok, le fac dupaia GONE
        inputError.visibility = View.VISIBLE

        if (feedbackInput == "") {
            inputError.text = "Opinia dvs trebuie introdusa!"
        } else if (feedbackInput.length < 3) {
            inputError.text = "Opinie mult prea scurta!"
        } else {
            inputError.text = ""
            inputError.visibility = View.GONE
        }

        // verific daca ii ascuns labelu de erori, ca daca e ascuns, inseamna ca e ok
        if (inputError.visibility == View.GONE) {
            // pregatesc labelu pentru feedback, sa spuna starea feedback-ului
            feedbackError.visibility = View.VISIBLE

            // verific daca am ceva in label-urile de erori
            if (inputError.text.equals("") && gradeError.text.equals("")) {
                progressBarFeedback.visibility = View.VISIBLE

                val intent = Intent(this, MenuActivity::class.java)

                feedbackError.text = "Feedback inregistrat"
                feedbackError.setTextColor(getColor(R.color.colorGreen))

                // disable-uiesc toate butoanele si orice din pagina, cat timp se trimite feedback-ul (cat timp apare ProgressBar-ul pe ecran)
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )

                // las sa se invarta atata timp ProgressBar-u pana sa il fac invizibil si sa trec la urmatoarea activitate
                //Timer("SettingUp", false).schedule(0) {
                    Handler(mainLooper).postDelayed({
                        progressBarFeedback.visibility = View.GONE
                        startActivity(intent)
                    }, 3000)
                //}
            } else {
                feedbackError.text = "Feedback incomplet"
                feedbackError.setTextColor(getColor((R.color.colorRed)))
            }
        }
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Renuntati la feedback?")
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

    private fun fillDropdownWithValues() {
        val spinner: Spinner = findViewById(R.id.gradesDropdown)
        // creez un ArrayAdapter folosind array-ul ala de string-uri din strings.xml si layout-u default pentru spinner (dropdown)
        ArrayAdapter.createFromResource(
            this,
            R.array.gradesArray,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // ce layout folosesc cand apare lista de variante
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // aplic adaptoru la elementu de tip spinner (dropdown)
            spinner.adapter = adapter
        }
    }

    // cand apas ca vreau sa trimit formularul, atunci fac verificarile
    val trimiteBtnClick = { dialog: DialogInterface, which: Int ->
        //Toast.makeText(applicationContext, "Feedback trimis!", Toast.LENGTH_SHORT).show();
        sendFeedback()
    }
    val cancelBtnClick = { dialog: DialogInterface, which: Int ->
        //Toast.makeText(applicationContext, android.R.string.no, Toast.LENGTH_SHORT).show()
        dialog.cancel()
    }

    fun basicAlert(view: View) {

        val builder = AlertDialog.Builder(this)
        with(builder)
        {
            setTitle("Trimitere feedback")
            setMessage("Sunteti sigur?")
            setPositiveButton("DA", DialogInterface.OnClickListener(function = trimiteBtnClick))
            setNegativeButton("NU", DialogInterface.OnClickListener(function = cancelBtnClick))
            show()
        }
    }
}