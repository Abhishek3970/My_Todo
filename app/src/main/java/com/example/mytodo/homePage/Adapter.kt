package com.example.mytodo.homePage

import android.service.autofill.TextValueSanitizer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.R
import com.example.mytodo.database.Item

class Adapter : RecyclerView.Adapter<Adapter.myViewHolder>() {

    var data = listOf<Item>()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        return myViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.heading.text = data[position].heading
        holder.description.text = data[position].description
        holder.time.text = data[position].time.toString()
    }


    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val heading: TextView = itemView.findViewById(R.id.heading_ad)
        val description: TextView = itemView.findViewById(R.id.description_ad)
        val time: TextView = itemView.findViewById(R.id.textView_time)
    }
}