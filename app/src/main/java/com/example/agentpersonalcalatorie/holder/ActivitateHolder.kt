package com.example.agentpersonalcalatorie.holder

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agentpersonalcalatorie.R
import com.example.agentpersonalcalatorie.model.Activitate
import com.example.agentpersonalcalatorie.model.MyTrip
import com.squareup.picasso.Picasso

class ActivitateHolder (private val view: View) :
    RecyclerView.ViewHolder(view) {
    private lateinit var titleRef: TextView
    private lateinit var hourRef: TextView
    private lateinit var desscRef: TextView
    private lateinit var data: Activitate


    init {
        titleRef = view.findViewById(R.id.titleInput)
        hourRef = view.findViewById(R.id.timeInput)
        desscRef = view.findViewById(R.id.descriptionInput)

    }

    @SuppressLint("SetTextI18n")
    fun bindData(data: Activitate) {
        this.data = data
        titleRef.text = data.title
        hourRef.text = data.hour
        desscRef.text = data.text

    }

}