package com.example.mytodo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "item_table")
data class Item (

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "heading")
    var heading: String = "heading",

    @ColumnInfo(name = "description")
    var description: String = "description",

    @ColumnInfo(name = "time")
    var time: Long = System.currentTimeMillis()

)