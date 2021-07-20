package com.example.agentpersonalcalatorie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.agentpersonalcalatorie.R
import com.example.agentpersonalcalatorie.model.Share

class ShareAdapter(var context: Context, var persons: ArrayList<Share>, var favorites: ArrayList<Share>) : BaseAdapter() {

    private class ViewHolder(row: View?) {
        var numeShare: TextView = row?.findViewById(R.id.numeShare) as TextView
        var emailShare: TextView = row?.findViewById(R.id.emailShare) as TextView
        //var durataShare: TextView = row?.findViewById(R.id.durataShare) as TextView
        var imagineShare: ImageView = row?.findViewById(R.id.imagineShare) as ImageView
        var iconita_stea: ImageView = row?.findViewById(R.id.iconita_stea) as ImageView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder

        val persoanaShare: Share = getItem(position) as Share

        // daca inca nu am pentru view-ul asta, atunci creez unu pe baza la produsele existente
        if (convertView == null) {
            val layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.share_person_card, parent, false)

            if (favorites.contains(persoanaShare)) {
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorLightGreen))
            }
            else {
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite))
            }
            
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            // daca am deja, atunci doar fac corespondenta intre tag
            view = convertView

            if (favorites.contains(persoanaShare)) {
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorLightGreen))
            }
            else {
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite))
            }

            viewHolder = view.tag as ViewHolder
        }

        // imi atribui dinamic valorile la persoanaShare
        viewHolder.numeShare.text = persoanaShare.nume
        viewHolder.emailShare.text = persoanaShare.email
        //viewHolder.durataShare.text = persoanaShare.durata
        viewHolder.imagineShare.setImageResource(persoanaShare.imagine)
        viewHolder.iconita_stea.setImageResource(persoanaShare.iconita_stea)

        if (favorites.contains(persoanaShare)) {
            viewHolder.iconita_stea.visibility = View.VISIBLE
        }
        else {
            viewHolder.iconita_stea.visibility = View.GONE
        }

        return view as View
    }

    override fun getItem(position: Int): Any {
        return persons[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return persons.count()
    }
}