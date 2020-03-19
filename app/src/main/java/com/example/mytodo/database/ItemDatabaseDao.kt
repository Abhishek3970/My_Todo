package com.example.mytodo.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ItemDatabaseDao{

    @Insert
    fun insert(item: Item)

    @Update
    fun update(item: Item)

    @Delete
    fun delete(item: Item)

    @Query("SELECT * from item_table WHERE id = :key")
    fun get(key: Long): Item

    @Query("DELETE from item_table")
    fun clear()

    @Query("SELECT * from item_table ORDER BY id DESC")
    fun getAllItem(): LiveData<List<Item>>

}