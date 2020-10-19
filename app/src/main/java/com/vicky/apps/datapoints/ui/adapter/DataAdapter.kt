package com.vicky.apps.datapoints.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.ui.model.CountryBasicInfo


class DataAdapter constructor(var data: List<CountryBasicInfo>) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_child_view,parent,false)
        return DataViewHolder(v)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        Picasso.get().load(data[position].flag)
            .placeholder(R.drawable.avatar)
            .error(R.drawable.avatar)
            .into(holder.flagView)

        holder.countryName.text = data[position].name
    }

    fun updateData(loadData: List<CountryBasicInfo>){
        this.data = loadData
        notifyDataSetChanged()
    }

    class DataViewHolder(v:View): RecyclerView.ViewHolder(v){
        var flagView :ImageView = v.findViewById(R.id.flagView)
        var countryName :TextView = v.findViewById(R.id.countryName)
    }
}