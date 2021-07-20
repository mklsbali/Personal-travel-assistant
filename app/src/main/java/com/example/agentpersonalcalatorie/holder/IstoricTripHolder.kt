package com.example.agentpersonalcalatorie.holder

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agentpersonalcalatorie.R
import com.example.agentpersonalcalatorie.model.MyTrip
import com.squareup.picasso.Picasso

class IstoricTripHolder(private val view: View) :
    RecyclerView.ViewHolder(view) {
    private lateinit var numeRef: TextView
    private lateinit var dataRef: TextView
    private lateinit var priceRef: TextView
    private lateinit var imageRef: ImageView
    private lateinit var data: MyTrip


    init {
        numeRef = view.findViewById(R.id.destinatieInput)
        dataRef = view.findViewById(R.id.dataInput)
        priceRef = view.findViewById(R.id.pretInput)
        imageRef = view.findViewById(R.id.imageView2)
    }

    @SuppressLint("SetTextI18n")
    fun bindData(data: MyTrip) {
        this.data = data
        numeRef.text = data.title
        dataRef.text = data.date
        priceRef.text = data.price.toString()+"EUR"
        Picasso.get().load(data.image).into(imageRef);
    }

}