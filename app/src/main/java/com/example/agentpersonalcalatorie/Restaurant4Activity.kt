package com.example.agentpersonalcalatorie

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agentpersonalcalatorie.restaurantadapter.Foods
import com.example.agentpersonalcalatorie.restaurantadapter.MenuItem
import com.example.agentpersonalcalatorie.restaurantadapter.RestaurantAdapter

class Restaurant4Activity : AppCompatActivity() {
    var foodAdapter: RestaurantAdapter? = null
    var dataSource:ArrayList<MenuItem>?=null
    var totalPrice:Int=0
    var foods:ArrayList<String>?= arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant4)
        setTitle("Mancari")
        dataSource=Foods().getFoods()
        val listView = findViewById<ListView>(R.id.restaurantListView)
        listView.visibility = View.VISIBLE


        foodAdapter = RestaurantAdapter(this@Restaurant4Activity, dataSource as ArrayList<MenuItem>)

        listView.adapter = foodAdapter

        registerForContextMenu(listView)

        findViewById<Button>(R.id.inainteLaBauturiB).setOnClickListener({

            for (m in dataSource!!)
            {
                if (m.isSelected)
                {
                    totalPrice+=m.price
                    foods!!.add(m.title)
                }
            }
           // Toast.makeText(applicationContext,"Total price: "+totalPrice, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Restaurant5Activity::class.java)
            intent.putExtra("Total_price",totalPrice);
            intent.putExtra("Foods", foods);
            startActivity(intent)
        })

    }
}