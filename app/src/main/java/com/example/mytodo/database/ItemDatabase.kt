package com.example.mytodo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Item::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {

    abstract val itemDao: ItemDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: ItemDatabase? = null

        fun getInstance(context: Context): ItemDatabase {
            synchronized(true) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ItemDatabase::class.java,
                        "item_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }


    }

}