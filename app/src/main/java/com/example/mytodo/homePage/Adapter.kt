package com.example.mytodo.homePage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.database.Item
import com.example.mytodo.databinding.ItemBinding

class Adapter(val clickListener: ItemClickListener) : RecyclerView.Adapter<Adapter.myViewHolder>() {

    var data = listOf<Item>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        return myViewHolder.from(parent)
    }

    override fun getItemCount() = data.size


    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item,clickListener)
    }


    class myViewHolder private constructor(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item, clickListener: ItemClickListener) {
            binding.item = item
            binding.click = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): myViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBinding.inflate(layoutInflater , parent , false)
                return myViewHolder(binding)
            }
        }

    }

}


class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

}  //caused problems while hiding keyboard

class ItemClickListener( val clickListener: (Item)-> Unit ){
    fun onClick(item: Item) = clickListener(item)
}