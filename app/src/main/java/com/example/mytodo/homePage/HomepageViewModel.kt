package com.example.mytodo.homePage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytodo.database.ItemDatabaseDao
import kotlinx.coroutines.*

class HomepageViewModel(
    val database: ItemDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun clear(){                     //delete all from DB
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.clear()
            }
        }
    }

    var items = database.getAllItem()

    private var _toAddProduct = MutableLiveData<Boolean>()
    val toAddProduct: LiveData<Boolean>?
        get() = _toAddProduct


    init {
        _toAddProduct.value = false
    }

    fun toAdd() {
        _toAddProduct.value = true
    }

    fun doneToAdd() {
        _toAddProduct.value = false
    }


}
