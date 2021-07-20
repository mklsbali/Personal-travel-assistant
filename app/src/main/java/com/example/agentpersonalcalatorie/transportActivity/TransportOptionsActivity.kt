package com.example.agentpersonalcalatorie.transportActivity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.*
import android.widget.AdapterView.OnItemLongClickListener
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.agentpersonalcalatorie.MenuActivity
import com.example.agentpersonalcalatorie.R
import com.example.agentpersonalcalatorie.adapter.TransportOptionAdapter
import com.example.agentpersonalcalatorie.model.TransportOption
import com.example.agentpersonalcalatorie.model.TransportOptions
import kotlinx.android.synthetic.main.transport.*
import kotlinx.android.synthetic.main.transport.endInput
import kotlinx.android.synthetic.main.transport.startInput
import kotlinx.android.synthetic.main.transport_options.*
import kotlinx.android.synthetic.main.transport_options.progressBarTransport
import java.util.*
import kotlin.concurrent.schedule


class TransportOptionsActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var currentCategory = "CAR"
    var transportList: ArrayList<TransportOption> = ArrayList()
    var listedOptions: ArrayList<TransportOption> = ArrayList()
    lateinit var optionsAdapter: TransportOptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transport_options)

        optionsListView.visibility = View.GONE
        
        val optionsListView = findViewById<ListView>(R.id.optionsListView)
        
        transportList = TransportOptions.instance.fillRoutesWithOptions()
        
        // inregistram acest view ca fiind pentru meniu contextual
        registerForContextMenu(optionsListView)

        optionsAdapter = TransportOptionAdapter(applicationContext, listedOptions)
        optionsListView.adapter = optionsAdapter
        
        Timer("SettingUp", false).schedule(2000) {
            Handler(mainLooper).post {
                startInput.visibility = View.VISIBLE
                endInput.visibility = View.VISIBLE
                //chooseRouteBtn.visibility = View.VISIBLE

                val startAddress = intent.getStringExtra("startAddress")
                val endAddress = intent.getStringExtra("endAddress")

                // resetez campurile de fiecare data cand se incarca pagina de transport
                startInput.setText(startAddress)
                endInput.setText(endAddress)
            }
        }
        optionsListView.setLongClickable(true)

        val radioGroup = findViewById<RadioGroup>(R.id.transportRadioGroup)
        radioGroup.setOnCheckedChangeListener { _, checkedId -> // checkedId is the RadioButton selected
            switchCategory(checkedId)
        }

        showProducts(currentCategory)
    }

    private fun switchCategory(checkedId: Int) {
        when (checkedId) {
            R.id.carRadioBtn -> {
                Toast.makeText(this, "CAR", Toast.LENGTH_SHORT).show()
                currentCategory = "CAR"
            }
            R.id.busRadioBtn -> {
                Toast.makeText(this, "BUS", Toast.LENGTH_SHORT).show()
                currentCategory = "BUS"
            }
            R.id.walkRadioBtn -> {
                Toast.makeText(this, "WALK", Toast.LENGTH_SHORT).show()
                currentCategory = "WALK"
            }
            R.id.taxiRadioBtn -> {
                Toast.makeText(this, "TAXI", Toast.LENGTH_SHORT).show()
                currentCategory = "TAXI"
            }
            else -> {
                Toast.makeText(this, "NONE", Toast.LENGTH_SHORT).show()
                currentCategory = "NONE"
            }
        }
            showProducts(currentCategory)
    }

    private fun showProducts(category: String) {
        progressBarTransport.bringToFront()
        progressBarTransport.visibility = View.VISIBLE;
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

        // las sa se invarta atata timp progressBarTransport-u pana sa il fac invizibil
        Handler(mainLooper).postDelayed({
            progressBarTransport.visibility = View.GONE;
            // trebuie facuta vizibila, dupa acea prima data cand nu se afiseaza nimic
            optionsListView.visibility = View.VISIBLE

            // acum filtram dupa produsele din categoria selectata
            listedOptions.clear()
            for (o in transportList) {
                if (o.categorie.compareTo(category) == 0) {
                    listedOptions.add(o)
                }
            }

            optionsAdapter.notifyDataSetChanged()
            optionsListView.adapter = optionsAdapter
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }, 3000)
    }

    // la meniu asta contextual, ii dau layout-u pe care l-am creat in operations_menu
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //menu.setHeaderTitle("Puteti efectua: ")
        menuInflater.inflate(R.menu.transport_select_options, menu)
    }

    // cand e selectat un buton din meniul contextual, o sa fac cateva operatii
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val menuInfo: AdapterView.AdapterContextMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo

        when (item.itemId) {
            // PORNESTE PE RUTA SELECTATA
            R.id.porneste -> {
                Toast.makeText(this, "Pregatire traseu", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, TransportRoute::class.java)

                window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )

                startActivity(intent)
            }

            // DETAII DESPRE RUTA SELECTATA
            R.id.detalii_ruta -> {
                Toast.makeText(this, "Detalii ruta", Toast.LENGTH_SHORT).show()

                window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )

                var dialog: AlertDialog? = null
                val builder = AlertDialog.Builder(this)
                // set the custom layout
                val view = layoutInflater.inflate(R.layout.transport_route_end_details, null)
                // Get Views references from your layout

                val categorie: TextView = view.findViewById(R.id.categorieRuta)
                val timp: TextView = view.findViewById(R.id.timpRuta)
                val km: TextView = view.findViewById(R.id.kmRuta)
                val descriere: TextView = view.findViewById(R.id.descriereRuta)
                val linie: TextView = view.findViewById(R.id.linieRuta)
                val caloriiArse: TextView = view.findViewById(R.id.caloriiArseRuta)
                val pretAprox: TextView = view.findViewById(R.id.pretAproxRuta)
                val numarMasina: TextView = view.findViewById(R.id.numarMasinaRuta)


                categorie.text = "Categorie: " + listedOptions[menuInfo.position].categorie
                timp.text = "Timp aproximativ: " + listedOptions[menuInfo.position].km.toString()
                km.text = "Km: " + listedOptions[menuInfo.position].timp
                descriere.text = "Descriere: " + listedOptions[menuInfo.position].descriere

                categorie.visibility = View.VISIBLE
                timp.visibility = View.VISIBLE
                km.visibility = View.VISIBLE
                descriere.visibility = View.VISIBLE

                if (listedOptions[menuInfo.position].categorie.equals("BUS")) {
                    linie.text = "Linie de autobuz: " + listedOptions[menuInfo.position].linie
                    linie.visibility = View.VISIBLE
                }
                else {
                    linie.visibility = View.GONE
                }

                if (listedOptions[menuInfo.position].categorie.equals("WALK")) {
                    caloriiArse.text = "Nr. aprox. calorii arse: " + listedOptions[menuInfo.position].caloriiArse.toString()
                    caloriiArse.visibility = View.VISIBLE
                }
                else {
                    caloriiArse.visibility = View.GONE
                }

                if (listedOptions[menuInfo.position].categorie.equals("TAXI")) {
                    pretAprox.text = "Pret aprox.: " + listedOptions[menuInfo.position].pretAprox.toString() + " lei"
                    numarMasina.text = "Nr masina: " + listedOptions[menuInfo.position].numarMasina
                    pretAprox.visibility = View.VISIBLE
                    numarMasina.visibility = View.VISIBLE
                }
                else {
                    pretAprox.visibility = View.GONE
                    numarMasina.visibility = View.GONE
                }

                builder.setView(view)
                // create and show the alert dialog
                dialog = builder.create()
                dialog.setCanceledOnTouchOutside(true)
                dialog.setCancelable(true)
                dialog.show()
                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Renuntati la cautare de rute?")
            .setPositiveButton("Da", DialogInterface.OnClickListener { dialog, id ->
                finish()
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            })
            .setNegativeButton(
                "Nu",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        builder.create().show()

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }
}