package com.beyond.sellandtrack.screens.manage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.beyond.sellandtrack.data.Repository
import com.beyond.sellandtrack.data.dataClasses.Product
import com.beyond.sellandtrack.databinding.FragmentAddProductBinding
import kotlinx.coroutines.launch

class AddProductViewModel(application: Application):AndroidViewModel(application) {

    private lateinit var repository: Repository
    init{
        repository = Repository()
    }

    fun addProductUiCheck(binding: FragmentAddProductBinding) : Boolean{
        if(binding.itemCodeEdittext.text.toString().isEmpty()) {
            binding.itemCodeEdittext.error = "Please Enter Name"
            binding.itemCodeEdittext.requestFocus()
            return false
        }
        else if(binding.itemNameEdittext.text.toString().isEmpty()){
            binding.itemNameEdittext.error = "Please Enter Email"
            binding.itemNameEdittext.requestFocus()
            return false
        }
        else if(binding.itemQtyEdittext.text.toString().isEmpty()) {
            binding.itemQtyEdittext.error = "Please Enter Password"
            binding.itemQtyEdittext.requestFocus()
            return false
        }
        else if(binding.itemPriceEdittext.text.toString().isEmpty()){
            binding.itemPriceEdittext.error = "Please Enter Password"
            binding.itemPriceEdittext.requestFocus()
            return false
        }
        return true
    }

    fun addProduct(binding: FragmentAddProductBinding?) =viewModelScope.launch {
        val name = binding?.itemNameEdittext?.text.toString()
        val itemCode=  binding?.itemCodeEdittext?.text.toString()
        val quantity =  binding?.itemQtyEdittext?.text.toString()
        val price = binding?.itemPriceEdittext?.text.toString()
        val uid = repository.getUserUid()
        val product = Product(itemCode,name,price,quantity,uid)
        repository.addProduct(product)
    }

}