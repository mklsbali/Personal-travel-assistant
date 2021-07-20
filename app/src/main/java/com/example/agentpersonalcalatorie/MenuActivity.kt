package com.example.agentpersonalcalatorie

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.agentpersonalcalatorie.shareActivity.ShareActivity
import com.example.agentpersonalcalatorie.shareActivity.ShareShowMap
import com.example.agentpersonalcalatorie.transportActivity.TransportActivity
import kotlinx.android.synthetic.main.menu.*
import java.util.*
import kotlin.concurrent.schedule

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

        Timer("SettingUp", false).schedule(2000) {
            Handler(mainLooper).post {

                val progressBar = findViewById<ProgressBar>(R.id.progressBar)
                progressBar.visibility = View.GONE
                button1.visibility = View.VISIBLE
                button2.visibility = View.VISIBLE
                shareBtn.visibility = View.VISIBLE
                button4.visibility = View.VISIBLE
                button5.visibility = View.VISIBLE
                transportBtn.visibility = View.VISIBLE
                feedbackBtn.visibility = View.VISIBLE
                button8.visibility = View.VISIBLE
                button9.visibility = View.VISIBLE

                // add event on feedback button
                var feedbackBtn = findViewById<Button>(R.id.feedbackBtn)
                feedbackBtn.setOnClickListener {
                    feedbackView()
                }

                // add event on transport button
                var transportBtn = findViewById<Button>(R.id.transportBtn)
                transportBtn.setOnClickListener {
                    transportView()
                }

                var tripsBtn = findViewById<Button>(R.id.button4)
                tripsBtn.setOnClickListener {
                    tripsView();
                }
                var restaurantBtn = findViewById<Button>(R.id.button1)
                restaurantBtn.setOnClickListener {
                    restaurantView()
                }

                var istoricBtn=findViewById<Button>(R.id.button5)
                istoricBtn.setOnClickListener {
                    istoricView()
                }

                var objectiveTBtn=findViewById<Button>(R.id.button8)
                objectiveTBtn.setOnClickListener {
                    obiectiveTuristice()
                }

                var shareBtn=findViewById<Button>(R.id.shareBtn)
                shareBtn.setOnClickListener {
                    shareView()
                }
            }
        }
    }

    private fun tripsView() {
        val intent = Intent(this, MyTripsActivity::class.java)
        startActivity(intent)
    }

    // open the feedback functionality
    private fun feedbackView() {
        val intent = Intent(this, FeedbackActivity::class.java)
        startActivity(intent)
    }

    // open the transport functionality
    private fun transportView() {
        val intent = Intent(this, TransportActivity::class.java)
        startActivity(intent)
    }

    private fun restaurantView() {
        val intent = Intent(this, RestaurantActivity::class.java)
        startActivity(intent)
    }
    private fun istoricView() {
        val intent = Intent(this, IstoricActivity::class.java)
        startActivity(intent)
    }
    private fun obiectiveTuristice() {
        val intent = Intent(this, ObiectiveTuristice1Activity::class.java)
        startActivity(intent)
    }

    // open the transport functionality
    private fun shareView() {
        val intent = Intent(this, ShareShowMap::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Doriti sa va delogati?")
            .setPositiveButton("Da", DialogInterface.OnClickListener { dialog, id ->
                finish()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            })
            .setNegativeButton(
                "Nu",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        builder.create().show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logoutID) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
