package com.example.agentpersonalcalatorie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.agentpersonalcalatorie.R
import com.example.agentpersonalcalatorie.holder.ActivitateHolder
import com.example.agentpersonalcalatorie.holder.IstoricTripHolder
import com.example.agentpersonalcalatorie.model.Activitate
import com.example.agentpersonalcalatorie.model.MyTrip

class ActivitateAdapter (private val context: Context, private val dataSource: ArrayList<Activitate>) :
    RecyclerView.Adapter<ActivitateHolder>() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitateHolder {
        val view = inflater.inflate(viewType, parent, false)
        return ActivitateHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.activitate_item
    }


    override fun getItemCount(): Int {
        return dataSource.size;
    }

    override fun onBindViewHolder(holder: ActivitateHolder, position: Int) {
        holder.bindData(dataSource.get(position))
    }

    fun addItem(item: Activitate) {
        dataSource.add(item)
        notifyDataSetChanged()
    }
}