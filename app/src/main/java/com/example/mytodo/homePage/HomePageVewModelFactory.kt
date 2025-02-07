package com.example.mytodo.homePage

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mytodo.database.ItemDatabase
import com.example.mytodo.database.ItemDatabaseDao

class HomePageVewModelFactory(
    private val dataSource: ItemDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomepageViewModel::class.java)) {
            return HomepageViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}