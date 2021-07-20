package com.example.agentpersonalcalatorie

import Suggestions
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.agentpersonalcalatorie.adapter.SugestiAdapter
import com.example.agentpersonalcalatorie.model.Suggestion
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class SuggestionsActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    var sugestiAdapter: SugestiAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.suggestions_list)

        Timer("SettingUp", false).schedule(1000) {
            Handler(mainLooper).post {
                val progressBar = findViewById<ProgressBar>(R.id.progressBar)
                progressBar.visibility = View.GONE
                val listView = findViewById<ListView>(R.id.list)
                listView.visibility = View.VISIBLE

                val id = intent.getSerializableExtra("TRIP_ID") as? Int
                sugestiAdapter = SugestiAdapter(
                    this@SuggestionsActivity,
                    Suggestions.instance.getSagestByID(id) as ArrayList<Suggestion>
                )

                listView.adapter = sugestiAdapter
                registerForContextMenu(listView)
                listView.onItemClickListener = this@SuggestionsActivity
            }
        }

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Doriti sa finalizati actiunea?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                val suggestion = sugestiAdapter!!.getItem(position) as Suggestion
                Suggestions.instance.take(suggestion.id)
                this.recreate();
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, witch ->

            })

        builder.create().show()
    }
}