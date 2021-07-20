package com.example.agentpersonalcalatorie

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule
import kotlin.random.Random.Default.nextInt


class PlataCard1Activity : AppCompatActivity() {
    var produse:ArrayList<String>?= arrayListOf<String>()
    var pret:Int=0
    var produseString:String=""
    lateinit var titularError:TextView
    lateinit var nrContError:TextView
    lateinit var cvcError:TextView
    lateinit var progressBar:ProgressBar
    lateinit var progressBarText:TextView
    lateinit var codConfirmareLabel:TextView
    lateinit var codConfirmare:EditText
    lateinit var codConfServer:TextView
    lateinit var codConfError:TextView
    lateinit var button:Button
    var cod:Int=0
    var progressVisible:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plata_card1)
        setTitle("Plata cu card")



        produse=intent.getStringArrayListExtra("Produse")
        pret=intent.getIntExtra("Pret",0)

        for (p in produse!!)
        {
            produseString+="-"+p+"\n"
        }

        findViewById<TextView>(R.id.produseCard).text=produseString
        findViewById<TextView>(R.id.pretCard).text=pret.toString()+" lei"

        titularError=findViewById<TextView>(R.id.titularError)
        titularError.visibility=View.GONE
        nrContError=findViewById<TextView>(R.id.numarContError)
        nrContError.visibility= View.GONE
        cvcError=findViewById<TextView>(R.id.cvcError)
        cvcError.visibility= View.GONE
        progressBar=findViewById<ProgressBar>(R.id.cardProgressBar)
        progressBar.visibility=View.GONE
        progressBarText=findViewById<TextView>(R.id.cardPrText)
        progressBarText.visibility=View.GONE

        codConfirmareLabel=findViewById<TextView>(R.id.codConfirmareLabel)
        codConfirmareLabel.visibility=View.GONE
        codConfirmare=findViewById<EditText>(R.id.codConfirmare)
        codConfirmare.visibility=View.GONE
        codConfServer=findViewById<TextView>(R.id.codConfServer)
        codConfServer.visibility=View.GONE
        codConfError=findViewById<TextView>(R.id.codConfError)
        codConfError.visibility=View.GONE



        findViewById<Button>(R.id.verificareDateB).setOnClickListener({
            verificareDate()
        })
        button=findViewById<Button>(R.id.plataCardB)
        button.isEnabled=false
        button.setOnClickListener({
            plateste()
        })

    }

    private fun verificareDate() {

        var err1:Boolean=false
        var err2:Boolean=false
        var err3:Boolean=false

        var titularCont = findViewById<EditText>(R.id.titular)
        var numarCont = findViewById<EditText>(R.id.nrCont)
        var cvc = findViewById<EditText>(R.id.cvc)
        if (titularCont.text.isEmpty()) {
            titularError.text = "!! Acest camp este obligatoriu !!"
            titularError.visibility = View.VISIBLE
            err1=true
        } else {
            titularError.visibility = View.GONE
            err1=false
        }

        if (numarCont.text.isEmpty()) {
            nrContError.text = "!! Acest camp este obligatoriu !!"
            nrContError.visibility = View.VISIBLE
            err2=true
        } else if (numarCont.length() != 16) {
            nrContError.text = "!! Numarul de cont este invalid !!"
            nrContError.visibility = View.VISIBLE
            err2=true
        } else {
            nrContError.visibility = View.GONE
            err2=false
        }

        if (cvc.text.isEmpty()) {
            cvcError.text = "!! Acest camp este obligatoriu !!"
            cvcError.visibility = View.VISIBLE
            err3=true
        } else if (cvc.length() != 3) {
            cvcError.text = "!! CVC invalid !!"
            cvcError.visibility = View.VISIBLE
            err3=true
        } else {
            cvcError.visibility = View.GONE
            err3=false
        }

        if (!err1 && !err2 && !err3)
        {
            cod=(1000..9999).random()
            progressBar.visibility=View.VISIBLE
            progressBarText.visibility=View.VISIBLE
            Timer("SettingUp", false).schedule(2000) {
                Handler(mainLooper).post{
                    progressBar.visibility=View.GONE
                    progressBarText.visibility=View.GONE
                    codConfirmareLabel.visibility=View.VISIBLE
                    codConfirmare.visibility=View.VISIBLE
                    codConfServer.visibility=View.VISIBLE

                    codConfServer.text="Cod confirmare: "+cod.toString()
                    codConfServer.visibility=View.VISIBLE

                    button.isEnabled=true
                }
            }

        }

    }
    private fun plateste(){

        if (codConfirmare.text.toString().equals(cod.toString()))
        {
            codConfError.visibility=View.GONE
            val intent = Intent(this, PlataCard2Activity::class.java)
            startActivity(intent)

        }
        else if (codConfirmare.text.isEmpty())
        {
            codConfError.text="!! Te rog sa introduci codul de confirmare !!"
            codConfError.visibility=View.VISIBLE
        }
        else
        {
            codConfError.text="!! Cod incorect !!"
            codConfError.visibility=View.VISIBLE
        }
    }
}