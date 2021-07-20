package com.example.agentpersonalcalatorie.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.agentpersonalcalatorie.R
import com.example.agentpersonalcalatorie.model.MyTrip

class MyTripAdapter(
    private val context: Context,
    private var dataSource: ArrayList<MyTrip>
) : BaseAdapter() {
    // get a reference to the LayoutInflater service
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, containerView: View?, viewGroupParent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.my_trip_item, viewGroupParent, false)

        val tripTitle = rowView.findViewById<TextView>(R.id.title)
        val tripImage = rowView.findViewById<ImageView>(R.id.imageView)
        val tripPeriod=rowView.findViewById<TextView>(R.id.period)

        tripTitle.text = dataSource.get(position).title
        tripPeriod.text="Perioada: "+dataSource.get(position).date
        tripImage.setImageResource(dataSource.get(position).image)

        return rowView
    }


    override fun getItem(position: Int): Any {
        return dataSource.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }


}