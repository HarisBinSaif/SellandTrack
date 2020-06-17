package com.beyond.sellandtrack.screens.customers

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beyond.sellandtrack.data.Repository
import com.beyond.sellandtrack.data.dataClasses.Customer
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.launch

class CustomersViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var repository: Repository
    var customers : MutableLiveData<List<Customer>> = MutableLiveData()


    init{
        repository = Repository()
    }

    fun getCustomers() = viewModelScope.launch{
        repository.getCustomers().addSnapshotListener(EventListener<QuerySnapshot>{value,e->
            if(e!=null){
                return@EventListener
            }

            val customersList : MutableList<Customer>  = mutableListOf()
            for (doc in value!!){
                var customer = doc.toObject(Customer::class.java)
                customersList.add(customer)
            }
            customers.value = customersList

        })

    }

    fun addCustomer(number: String, name: String) = viewModelScope.launch {
        val customer = Customer(name,number,repository.getUserUid())
        repository.addCustomer(customer)
    }

}