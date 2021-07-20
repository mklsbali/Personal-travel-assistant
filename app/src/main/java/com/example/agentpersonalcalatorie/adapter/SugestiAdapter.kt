package com.example.agentpersonalcalatorie.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.example.agentpersonalcalatorie.R
import com.example.agentpersonalcalatorie.model.Suggestion

class SugestiAdapter(
    private val context: Context,
    private var dataSource: ArrayList<Suggestion>
) : BaseAdapter() {
    // get a reference to the LayoutInflater service
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, containerView: View?, viewGroupParent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.suggestions_item, viewGroupParent, false)

        val name = rowView.findViewById<TextView>(R.id.name)
        val imageView = rowView.findViewById<ImageView>(R.id.imageView)
        val checkBox = rowView.findViewById<CheckBox>(R.id.checkBox)

        name.text = dataSource.get(position).name
        imageView.setImageResource(dataSource.get(position).image)
        if (dataSource.get(position).take) {
            checkBox.text = name.text
            checkBox.isChecked = true
        } else {
            checkBox.text = name.text
        }

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