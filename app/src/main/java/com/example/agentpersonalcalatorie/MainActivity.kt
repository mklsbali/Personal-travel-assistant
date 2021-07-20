package com.example.agentpersonalcalatorie

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        UserInput.text.clear()
        PasswordInput.text.clear()
        ErrorLogin.clearComposingText()

        var button = findViewById<Button>(R.id.buttonLogin)
        button.setOnClickListener {
            onClickButton()
        }
    }

    private fun onClickButton() {
        var userInput = findViewById<EditText>(R.id.UserInput).text.toString()
        var passInput = findViewById<EditText>(R.id.PasswordInput).text.toString()

        var errorUser = findViewById<TextView>(R.id.errorUsername)
        var errorPass = findViewById<TextView>(R.id.ErrorPassword)
        var errorLog = findViewById<TextView>(R.id.ErrorLogin)

        errorUser.visibility = View.VISIBLE
        errorPass.visibility = View.VISIBLE
        errorLog.visibility = View.GONE

        if (userInput.equals("")) {
            errorUser.text = "Introdu username!"

        } else if (userInput.length < 3) {
            errorUser.text = "Username invalid"
        } else {
            errorUser.visibility = View.GONE
        }

        if (passInput.equals("")) {
            errorPass.text = "Introdu parola!"

        } else if (passInput.length < 3) {
            errorPass.text = "Parola invalida"
        } else {
            errorPass.visibility = View.GONE
        }

        if (errorPass.visibility == View.GONE && errorUser.visibility == View.GONE) {
            errorLog.visibility = View.VISIBLE
            if (userInput.equals("Client") && passInput.equals("client")) {
                errorLog.text = "Succes!"
                errorLog.setTextColor(getColor(R.color.colorGreen))
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            } else {
                errorLog.text = "Credentiale incorecte"
                errorLog.setTextColor(getColor((R.color.colorRed)))
            }
        }
    }

    // daca din meniul de login, apasa pe back, o sa apara un alert daca vrea sa paraseasca de tot aplicatia
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Iesiti din aplicatie?")
            .setPositiveButton("Da", DialogInterface.OnClickListener { dialog, id ->
                // termina toate procesele parinte si procesul curent, si deci se iese din aplicatie
                finishAffinity()
            })
            .setNegativeButton(
                "Nu",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        builder.create().show()
    }
}