package com.example.mytodo.homePage

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.mytodo.database.Item


@BindingAdapter("heading")
fun TextView.setHeading(item: Item){
    item?.let {
        text = item.heading
    }
}

@BindingAdapter("description")
fun TextView.setDescription(item: Item){
    item?.let{
        text = item.description
    }
}

@BindingAdapter("time")
fun TextView.time(item: Item){
    item?.let{
        text = item.time
    }
}