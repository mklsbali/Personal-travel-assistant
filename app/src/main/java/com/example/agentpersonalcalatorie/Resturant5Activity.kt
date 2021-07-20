package com.example.agentpersonalcalatorie

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.agentpersonalcalatorie.restaurantadapter.Drinks
import com.example.agentpersonalcalatorie.restaurantadapter.MenuItem
import com.example.agentpersonalcalatorie.restaurantadapter.RestaurantAdapter

class Restaurant5Activity : AppCompatActivity() {
    var foodAdapter: RestaurantAdapter? = null
    var dataSource:ArrayList<MenuItem>?=null
    var totalPrice:Int=0
    var foods:ArrayList<String>?= arrayListOf<String>()
    var drinks:ArrayList<String>?= arrayListOf<String>()
    var produse:ArrayList<String>?= arrayListOf<String>()
    var alertMessage:String="Mancari si bauturi comandate:\n\nMancari:\n"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant4)
        setTitle("Bauturi")
        totalPrice=intent.getIntExtra("Total_price",0)
        foods=intent.getStringArrayListExtra("Foods")

        findViewById<Button>(R.id.inainteLaBauturiB).text="Inainte la plata"
        dataSource= Drinks().getDrinks()
        val listView = findViewById<ListView>(R.id.restaurantListView)
        listView.visibility = View.VISIBLE


        foodAdapter = RestaurantAdapter(this@Restaurant5Activity, dataSource as ArrayList<MenuItem>)

        listView.adapter = foodAdapter

        registerForContextMenu(listView)

        findViewById<Button>(R.id.inainteLaBauturiB).setOnClickListener {

            for (m in dataSource!!) {
                if (m.isSelected) {
                    totalPrice+=m.price
                    drinks!!.add(m.title)
                }
            }

            for (f in foods!!)
            {
                alertMessage+="-"+f+"\n"
                produse!!.add(f)
            }
            alertMessage+="\nBauturi:\n"
            for (d in drinks!!)
            {
                alertMessage+="-"+d+"\n"
                produse!!.add(d)
            }
            alertMessage+="\nPret total: "+totalPrice+" lei"
            //Toast.makeText(applicationContext,"Total price: "+totalPrice, Toast.LENGTH_SHORT).show()

            val builder = AlertDialog.Builder ( this )
            builder.setTitle ( "Confirmare" )
                .setMessage ( alertMessage )
                .setPositiveButton ( " DA ", DialogInterface.OnClickListener{ _, _ ->
                    val intent = Intent(this, Plata1Activity::class.java)
                    intent.putExtra("Pret", totalPrice)
                    intent.putExtra("Produse",produse)
                    startActivity(intent)
                } )
                .setNegativeButton ( " NU, aleg noi mancari si bauturi", DialogInterface.OnClickListener{ _, _ ->
                    val intent = Intent(this, Restaurant4Activity::class.java)
                    startActivity(intent)
                } )
            builder.create().show()

        }


    }
}