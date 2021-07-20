package com.example.agentpersonalcalatorie.restaurantadapter


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.agentpersonalcalatorie.R


class RestaurantAdapter(private val context: Context, private var dataSource: ArrayList<MenuItem>) : BaseAdapter()
{

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @SuppressLint("ViewHolder")
    override fun getView (position: Int, containerView: View?, viewGroupParent: ViewGroup?): View
    {

        val rowView = inflater.inflate(R.layout.restaurant_menu_item, viewGroupParent, false)

        val offerTitle=rowView.findViewById<TextView>(R.id.menuName)
        val offerPrice=rowView.findViewById<CheckBox>(R.id.menuSelected)
        val offerImage=rowView.findViewById<ImageView>(R.id.menuImage)


        offerTitle.text= dataSource.get(position).title
        offerPrice.text=dataSource.get(position).price.toString()+" lei"
        offerImage.setImageResource(dataSource.get(position).image)

        offerPrice.setOnCheckedChangeListener({
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