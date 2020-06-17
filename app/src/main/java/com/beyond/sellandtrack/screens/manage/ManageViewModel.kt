package com.beyond.sellandtrack.screens.manage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.beyond.sellandtrack.data.Repository
import com.beyond.sellandtrack.screens.inventory.InventoryViewModel
import kotlinx.coroutines.launch

class ManageViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var repository: Repository

    fun deleteProduct(item_code :String) = viewModelScope.launch{
        repository.removeProduct(item_code)
    }

}