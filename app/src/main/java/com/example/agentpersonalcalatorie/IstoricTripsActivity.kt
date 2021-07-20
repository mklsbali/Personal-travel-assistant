package com.example.agentpersonalcalatorie

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agentpersonalcalatorie.adapter.IstoricTripsAdapter
import com.example.agentpersonalcalatorie.adapter.MyTripAdapter
import com.example.agentpersonalcalatorie.model.MyTrips
import kotlinx.android.synthetic.main.istoric_trips.*
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.concurrent.schedule

class IstoricTripsActivity : AppCompatActivity() {
    var myTripAdapter: MyTripAdapter? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var istoricAdapter: IstoricTripsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.istoric_trips)

        Timer("SettingUp", false).schedule(1500) {
            Handler(mainLooper).post {
                val progressBar = findViewById<ProgressBar>(R.id.progressBar2)
                val yearInput = findViewById<TextView>(R.id.yearInput)
                val searchBtn = findViewById<Button>(R.id.searchBtn)
                val recycler = findViewById<RecyclerView>(R.id.recycler)

                progressBar.visibility = View.GONE
                yearInput.visibility = View.VISIBLE
                searchBtn.visibility = View.VISIBLE
                recycler.visibility = View.VISIBLE

                linearLayoutManager = LinearLayoutManager(this@IstoricTripsActivity)

                searchBtn.setOnClickListener {
                    istoricAdapter = IstoricTripsAdapter(this@IstoricTripsActivity, ArrayList())
                    val recyclerView = findViewById<RecyclerView>(R.id.recycler)
                    recyclerView.layoutManager = linearLayoutManager
                    recyclerView.adapter = istoricAdapter
                    searchClick()
                }


            }
        }
    }

    private fun checkInput(): Boolean {
        val yearInput = findViewById<TextView>(R.id.yearInput)
        val input = yearInput.text.toString()
        return input!="" && input.toInt() >= 2000
    }

    private fun searchClick() {
        val loading = findViewById<ProgressBar>(R.id.progressBar4)
        val error = findViewById<TextView>(R.id.errorMsg)
        error.visibility = View.GONE
        loading.visibility = View.VISIBLE
        loading.progress = 50
        val ok = checkInput()
        Timer("SettingUp", false).schedule(1500) {
            Handler(mainLooper).post {
                loading.progress = 100
                Timer("SettingUp", false).schedule(100) {
                    Handler(mainLooper).post {
                        loading.visibility = View.GONE
                        if (ok) {
                            val yearInput = findViewById<TextView>(R.id.yearInput)
                            val input = yearInput.text.toString()
                            val date=input.toInt()
                            var trips = MyTrips.instance.getTripsByYear(date)
                            for (trip in trips)
                                istoricAdapter.addItem(trip)

                        } else {
                            error.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

    }
}