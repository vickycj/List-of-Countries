package com.vicky.apps.datapoints.ui.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.common.svgdecoder.SVGUtils
import com.vicky.apps.datapoints.data.local.entities.CountryEntity

class DetailsAdapter constructor(var data: List<CountryEntity>, var context: Context) : RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_child_view,
            parent,
            false
        )
        return DetailsViewHolder(v)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        SVGUtils.getSVGRequestBuilder(context)
            .load(Uri.parse(data[position].flag))
            .into(holder.flagView);

        holder.countryName.text = data[position].name
    }

    fun updateData(loadData: List<CountryEntity>){
        this.data = loadData
        notifyDataSetChanged()
    }

    class DetailsViewHolder(v: View): RecyclerView.ViewHolder(v){
        var flagView : ImageView = v.findViewById(R.id.flagView)
        var countryName : TextView = v.findViewById(R.id.countryName)
    }
}