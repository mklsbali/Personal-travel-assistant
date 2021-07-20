package com.example.agentpersonalcalatorie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.agentpersonalcalatorie.R
import com.example.agentpersonalcalatorie.holder.IstoricTripHolder
import com.example.agentpersonalcalatorie.model.MyTrip

class IstoricTripsAdapter (private val context: Context, private val dataSource: ArrayList<MyTrip>) :
RecyclerView.Adapter<IstoricTripHolder>() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IstoricTripHolder {
        val view = inflater.inflate(viewType, parent, false)
        return IstoricTripHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.istoric_trip_item
    }


    override fun getItemCount(): Int {
        return dataSource.size;
    }

    override fun onBindViewHolder(holder: IstoricTripHolder, position: Int) {
        holder.bindData(dataSource.get(position))
    }

    fun addItem(item: MyTrip) {
        dataSource.add(item)
        notifyDataSetChanged()
    }
}