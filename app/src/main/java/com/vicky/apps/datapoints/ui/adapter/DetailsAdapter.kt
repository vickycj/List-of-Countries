package com.vicky.apps.datapoints.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.ui.model.KeyValue

class DetailsAdapter constructor(var data: List<KeyValue>, var context: Context) : RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.details_recycler_child,
            parent,
            false
        )
        return DetailsViewHolder(v)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.title.text = data[position].tile
        holder.value.text = data[position].value
    }

    fun updateData(loadData: List<KeyValue>){
        this.data = loadData
        notifyDataSetChanged()
    }

    class DetailsViewHolder(v: View): RecyclerView.ViewHolder(v){
        var title : TextView = v.findViewById(R.id.title)
        var value : TextView = v.findViewById(R.id.value)
    }
}