package com.vicky.apps.datapoints.ui.adapter


import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ahmadrosid.svgloader.SvgLoader
import com.vicky.apps.datapoints.R

import com.vicky.apps.datapoints.data.local.entities.CountryEntity


class DataAdapter constructor(var data: List<CountryEntity>, var context: Activity,   var onItemClick: ((CountryEntity) -> Unit)) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_child_view,
            parent,
            false
        )
        return DataViewHolder(v)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        SvgLoader.pluck()
            .with(context)
            .setPlaceHolder(R.drawable.avatar, R.drawable.avatar)
            .load(data[position].flag, holder.flagView);

        holder.countryName.text = data[position].name

        holder.container.setOnClickListener { onItemClick(data[position]) }
    }

    fun updateData(loadData: List<CountryEntity>){
        this.data = loadData
        notifyDataSetChanged()
    }

    class DataViewHolder(v: View): RecyclerView.ViewHolder(v){
        var flagView :ImageView = v.findViewById(R.id.flagView)
        var countryName :TextView = v.findViewById(R.id.countryName)
        var container : ConstraintLayout = v.findViewById(R.id.childHeadView)
    }
}