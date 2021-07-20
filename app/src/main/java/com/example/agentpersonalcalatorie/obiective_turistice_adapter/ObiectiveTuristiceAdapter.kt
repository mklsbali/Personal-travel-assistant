package com.example.agentpersonalcalatorie.obiective_turistice_adapter

import android.annotation.SuppressLint
import android.content.Context
import android.media.Image
import android.service.autofill.TextValueSanitizer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.agentpersonalcalatorie.R
import com.example.agentpersonalcalatorie.restaurantadapter.MenuItem
import org.w3c.dom.Text

class ObiectiveTuristiceAdapter(private val context: Context, private var dataSource: ArrayList<Objective>) : BaseAdapter()
{

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @SuppressLint("ViewHolder")
    override fun getView (position: Int, containerView: View?, viewGroupParent: ViewGroup?): View
    {

        val rowView = inflater.inflate(R.layout.obiectiv_turistic, viewGroupParent, false)

        val objectiveName=rowView.findViewById<CheckBox>(R.id.objectiveName)
        val objectiveImage=rowView.findViewById<ImageView>(R.id.objectiveImage)
        val objectiveDescription=rowView.findViewById<TextView>(R.id.objectiveDescription)


        objectiveName.text= dataSource.get(position).title
        objectiveName.isChecked=dataSource.get(position).isSelected
        objectiveImage.setImageResource(dataSource.get(position).image)
        objectiveDescription.text=dataSource.get(position).description


        objectiveName.setOnCheckedChangeListener({
                buttonView, isChecked ->
            if (isChecked){
                dataSource.get(position).isSelected=true
            }else{
                dataSource.get(position).isSelected=false
            }
        })
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