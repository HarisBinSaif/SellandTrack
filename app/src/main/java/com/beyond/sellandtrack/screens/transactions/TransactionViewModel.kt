package com.beyond.sellandtrack.screens.transactions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beyond.sellandtrack.data.Repository
import com.beyond.sellandtrack.data.dataClasses.Product
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.launch

class TransactionViewModel(application: Application) :AndroidViewModel(application) {

    private lateinit var repository: Repository
    private var productsList : MutableList<Product> = mutableListOf()
    lateinit var product: MutableLiveData<Product>

    init {
        repository = Repository()
        product = MutableLiveData()
    }


    fun getProductInformation() = viewModelScope.launch {
        repository.getProductInfo("1004").addSnapshotListener(EventListener<QuerySnapshot>{value,e->
            if(e!=null){
                return@EventListener
            }

            productsList = mutableListOf()
            for (doc in value!!){
                var prod = doc.toObject(Product::class.java)
                if(prod.item_code=="1004"){
                    product.value = prod
                }
            }
        })

    }


}