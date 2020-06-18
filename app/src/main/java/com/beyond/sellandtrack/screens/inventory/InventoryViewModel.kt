package com.beyond.sellandtrack.screens.inventory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beyond.sellandtrack.data.Repository
import com.beyond.sellandtrack.data.dataClasses.Product
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.launch

class InventoryViewModel(application: Application): AndroidViewModel(application) {

    private var productsList : MutableList<Product> = mutableListOf()
    private lateinit var repository: Repository
    var inventory : MutableLiveData<List<Product>> = MutableLiveData()


    init {
        repository = Repository()
    }


    fun getProducts() = viewModelScope.launch{

        repository.getProducts().addSnapshotListener(EventListener<QuerySnapshot>{value,e->
            if(e!=null){
                return@EventListener
            }

            productsList = mutableListOf()
            for (doc in value!!){
                var product = doc.toObject(Product::class.java)
                productsList.add(product)
            }
            inventory.value = productsList
        })
    }

    fun changeQuantity(item_code:String, new_qty:String) = viewModelScope.launch {
        repository.changeQuantity(item_code,new_qty)
    }
}