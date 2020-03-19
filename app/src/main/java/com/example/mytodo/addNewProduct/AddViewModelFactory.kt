package com.example.mytodo.addNewProduct

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mytodo.database.ItemDatabaseDao
import com.example.mytodo.homePage.HomepageViewModel

class AddViewModelFactory(
    private val dataSource: ItemDatabaseDao,
    private val application: Application
) :ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddViewModel::class.java)) {
            return AddViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}