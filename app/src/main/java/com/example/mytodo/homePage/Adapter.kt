package com.example.mytodo.homePage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.R
import com.example.mytodo.database.Item

class Adapter : RecyclerView.Adapter<Adapter.myViewHolder>() {

    var data = listOf<Item>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        return myViewHolder.from(parent)
    }

    override fun getItemCount()= data.size


    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }


    class myViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val heading: TextView = itemView.findViewById(R.id.heading_ad)
        val description: TextView = itemView.findViewById(R.id.description_ad)
        val time: TextView = itemView.findViewById(R.id.textView_time)

        fun bind(item: Item) {
            heading.text = item.heading
            description.text = item.description
            time.text = item.time.toString()
        }

        companion object {
            fun from(parent: ViewGroup): myViewHolder {
                return myViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item, parent, false)
                )
            }
        }

    }

}


class ItemDiffCallback: DiffUtil.ItemCallback<Item>(){
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

}