package com.beyond.sellandtrack.screens.manage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.beyond.sellandtrack.R
import com.beyond.sellandtrack.databinding.FragmentAddProductBinding
/**
 * A simple [Fragment] subclass.
 */
class AddProductFragment : Fragment() {

    private lateinit var binding : FragmentAddProductBinding
    private lateinit var addProductViewModel: AddProductViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentAddProductBinding>(
            inflater,R.layout.fragment_add_product,container,false
        )

        addProductViewModel = ViewModelProvider(this).get(AddProductViewModel::class.java)
        //add product login here
        binding.saveProdButton.setOnClickListener {
           if(addProductViewModel.addProductUiCheck(binding)){
               addProductViewModel.addProduct(binding)
               showAlert()
               findNavController().navigate(R.id.action_addProductFragment_to_manageFragment)
           }
        }

        return binding.root
    }

    private fun showAlert() {
        val alertDialogBuilder = activity?.let { it1 -> AlertDialog.Builder(it1) }
        alertDialogBuilder?.setTitle("Product Added")
        alertDialogBuilder?.setMessage("New Product has been added successfully")
        alertDialogBuilder?.setCancelable(true)
        alertDialogBuilder?.create()
        alertDialogBuilder?.show()
    }


}
