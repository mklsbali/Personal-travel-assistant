package com.example.agentpersonalcalatorie.shareActivity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.*
import android.widget.AdapterView.OnItemLongClickListener
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import com.example.agentpersonalcalatorie.MenuActivity
import com.example.agentpersonalcalatorie.R
import com.example.agentpersonalcalatorie.adapter.ShareAdapter
import com.example.agentpersonalcalatorie.adapter.TransportOptionAdapter
import com.example.agentpersonalcalatorie.model.Share
import com.example.agentpersonalcalatorie.model.SharePersons
import com.example.agentpersonalcalatorie.model.TransportOptions
import kotlinx.android.synthetic.main.feedback.*
import kotlinx.android.synthetic.main.feedback.gradeError
import kotlinx.android.synthetic.main.share.*
import kotlinx.android.synthetic.main.transport.*
import kotlinx.android.synthetic.main.transport.endInput
import kotlinx.android.synthetic.main.transport.startInput
import kotlinx.android.synthetic.main.transport_options.*
import kotlinx.android.synthetic.main.transport_options.progressBarTransport
import java.util.*
import kotlin.concurrent.schedule


class ShareActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var personsList: ArrayList<Share> = ArrayList()
    var listedPersons: ArrayList<Share> = ArrayList()
    var favoriteList: ArrayList<Share> = ArrayList()
    lateinit var shareAdapter: ShareAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.share)

        personsListView.visibility = View.GONE

        val personsListView = findViewById<ListView>(R.id.personsListView)

        personsList = SharePersons.instance.fillPersonsCard()

        // inregistram acest view ca fiind pentru meniu contextual
        registerForContextMenu(personsListView)

        shareAdapter = ShareAdapter(applicationContext, listedPersons, favoriteList)
        personsListView.adapter = shareAdapter

        Timer("SettingUp", false).schedule(3000) {
            Handler(mainLooper).post {
                shareLocatieLbl.visibility = View.VISIBLE
                /*emailError.visibility = View.VISIBLE
                emailsDropdown.visibility = View.VISIBLE*/
                emailLbl.visibility = View.VISIBLE
                /*durataError.visibility = View.VISIBLE
                durataDropdown.visibility = View.VISIBLE
                durataLbl.visibility = View.VISIBLE
                shareLocatieError.visibility = View.VISIBLE*/

                // resetez campurile de fiecare data cand se incarca pagina de feedback
                /*durataDropdown.setSelection(0)
                durataDropdown.clearFocus()
                durataError.clearComposingText()*/
                /*emailsDropdown.setSelection(0)
                emailsDropdown.clearFocus()
                emailError.clearComposingText()*/
            }
        }
        personsListView.setLongClickable(true)
        showPersons()
    }

    private fun seteazaDurata() {

    }

    private fun showPersons() {
        progressBarShare.visibility = View.VISIBLE;

        // las sa se invarta atata timp ProgressBar-u pana sa il fac invizibil
        Handler(mainLooper).postDelayed({
            progressBarShare.visibility = View.GONE;
            // trebuie facuta vizibila, dupa acea prima data cand nu se afiseaza nimic
            personsListView.visibility = View.VISIBLE

            // acum filtram dupa produsele din categoria selectata
            listedPersons.clear()
            for (p in personsList) {
                listedPersons.add(p)
            }

            shareAdapter.notifyDataSetChanged()
            personsListView.adapter = shareAdapter
        }, 3000)
    }

    // la meniu asta contextual, ii dau layout-u pe care l-am creat in share_menu
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //menu.setHeaderTitle("Puteti efectua: ")
        menuInflater.inflate(R.menu.share_menu, menu)
    }

    // cand e selectat un buton din meniul contextual, o sa fac cateva operatii
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val menuInfo: AdapterView.AdapterContextMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo

        when (item.itemId) {
            // ADD LA FAVORITE
            R.id.adauga_persoana -> {
                AlertDialog.Builder(this)
                    .setTitle("Adauga favorit")
                    .setMessage("Doriti sa adaugati la favorite?")
                    .setPositiveButton(
                        "DA"
                    ) { dialog, which ->
                        // adaug la final in lista de favorite, elementul care in lista afisata se gaseste la pozitia meuInfo.position

                        if (favoriteList.contains(listedPersons[menuInfo.position])) {
                            Toast.makeText(this, "Exista deja la favorite", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            favoriteList.add(favoriteList.lastIndex + 1, listedPersons[menuInfo.position])
                            Toast.makeText(this, "Adaugat la favorite", Toast.LENGTH_SHORT).show()
                        }

                        shareAdapter.notifyDataSetChanged()
                    }
                    .setNegativeButton(
                        "NU"
                    ) { dialog, which ->
                        Toast.makeText(this, "Renuntat la add fav", Toast.LENGTH_SHORT).show()
                    }
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }

            // REMOVE DE LA FAVORITE
            R.id.elimina_persoana -> {
                AlertDialog.Builder(this)
                    .setTitle("Sterge favorit")
                    .setMessage("Doriti sa stergeti de la favorite?")
                    .setPositiveButton(
                        "DA"
                    ) { dialog, which ->

                        // daca nu e deja in lista de favorite, nu il pot sterge
                        if (!favoriteList.contains(listedPersons[menuInfo.position])) {
                            Toast.makeText(this, "Nu e favorit ca sa il poti sterge", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            // daca e deja in lista de favorite, doar atunci il pot sterge

                            // sterg din lista de favorite, elementul care in lista afisata se gaseste la pozitia meuInfo.position
                            favoriteList.remove(listedPersons[menuInfo.position])

                            shareAdapter.notifyDataSetChanged()
                            Toast.makeText(this, "Sters de la favorite", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .setNegativeButton(
                        "NU"
                    ) { dialog, which ->
                        // daca nu e deja in lista de favorite, nu pot face actiunea asta
                        if (!favoriteList.contains(listedPersons[menuInfo.position])) {
                            Toast.makeText(this, "Nu e favorit ca sa il poti face asta", Toast.LENGTH_SHORT).show()
                        }
                        shareAdapter.notifyDataSetChanged()
                    }
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
        }
        return super.onContextItemSelected(item)
    }

    //
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }

    // o sa apara un alert daca vrea sa paraseasca de tot aplicatia
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Renuntati la share locatie?")
            .setPositiveButton("DA", DialogInterface.OnClickListener { dialog, id ->
                finish()
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            })
            .setNegativeButton(
                "NU",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        builder.create().show()
    }
}