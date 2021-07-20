package com.example.agentpersonalcalatorie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.agentpersonalcalatorie.R
import com.example.agentpersonalcalatorie.model.TransportOption

class TransportOptionAdapter(
    var context: Context,
    var listedTransportOptions: ArrayList<TransportOption>
) : BaseAdapter() {

    private class ViewHolder(row: View?) {
        var categorie: TextView = row?.findViewById(R.id.categorie) as TextView
        var timp: TextView = row?.findViewById(R.id.timp) as TextView
        var km: TextView = row?.findViewById(R.id.km) as TextView
        var descriere: TextView = row?.findViewById(R.id.descriere) as TextView
        var linie: TextView = row?.findViewById(R.id.linie) as TextView
        var caloriiArse: TextView = row?.findViewById(R.id.caloriiArse) as TextView
        var pretAprox: TextView = row?.findViewById(R.id.pretAprox) as TextView
        var numarMasina: TextView = row?.findViewById(R.id.numarMasina) as TextView
        var imagine: ImageView = row?.findViewById(R.id.imagine) as ImageView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        val optiune: TransportOption = getItem(position) as TransportOption

        // daca inca nu am pentru view-ul asta, atunci creez unu pe baza la produsele existente
        if (convertView == null) {
            val layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.transport_item, parent, false)

            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            // daca am deja, atunci doar fac corespondenta intre tag
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        // imi atribui dinamic valorile
        viewHolder.categorie.text = optiune.categorie
        viewHolder.timp.text = optiune.timp
        viewHolder.km.text = optiune.km.toString() + "km"
        viewHolder.descriere.text = optiune.descriere
        viewHolder.linie.text = optiune.linie
        viewHolder.caloriiArse.text = optiune.caloriiArse.toString() + "kcal"
        viewHolder.pretAprox.text = optiune.pretAprox.toString()
        viewHolder.numarMasina.text = optiune.numarMasina
        viewHolder.imagine.setImageResource(optiune.imagine)

        when (optiune.categorie) {
            "CAR" -> {
                viewHolder.linie.visibility = View.GONE
                viewHolder.caloriiArse.visibility = View.GONE
                viewHolder.pretAprox.visibility = View.GONE
                viewHolder.numarMasina.visibility = View.GONE
            }
            "BUS" -> {
                viewHolder.linie.visibility = View.VISIBLE
                viewHolder.caloriiArse.visibility = View.GONE
                viewHolder.pretAprox.visibility = View.VISIBLE
                viewHolder.numarMasina.visibility = View.GONE
            }
            "TAXI" -> {
                viewHolder.linie.visibility = View.GONE
                viewHolder.caloriiArse.visibility = View.GONE
                viewHolder.pretAprox.visibility = View.VISIBLE
                viewHolder.numarMasina.visibility = View.VISIBLE
            }
            "WALK" -> {
                viewHolder.linie.visibility = View.GONE
                viewHolder.caloriiArse.visibility = View.VISIBLE
                viewHolder.pretAprox.visibility = View.GONE
                viewHolder.numarMasina.visibility = View.GONE
            }
        }

        return view as View
    }

    override fun getItem(position: Int): Any {
        return listedTransportOptions[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listedTransportOptions.count()
    }
}