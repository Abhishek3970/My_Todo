package com.example.mytodo.addNewProduct

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytodo.database.Item
import com.example.mytodo.database.ItemDatabaseDao
import kotlinx.coroutines.*

class AddViewModel(
    val database: ItemDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private val _done = MutableLiveData<Boolean>()
    val done: LiveData<Boolean>?
        get() = _done

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        _done.value = false
    }

    fun save(heading: String, description: String) {          //Add new item
        uiScope.launch {
            withContext(Dispatchers.IO) {
                val item = Item(heading = heading, description = description)
                database.insert(item)
            }
        }
        navigate()
    }

    fun update(item : Item){                                      //Update existing item
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.update(item)
            }
        }
        navigate()
    }

    private fun navigate() {
        _done.value = true
    }

    fun deleteAndNavigate(item: Item) {                              //delete an Item

        uiScope.launch {
            withContext(Dispatchers.IO){
                database.delete(item)
            }
        }

        navigate()
    }

    fun doneNavigation(){
        _done.value = false
    }
}
