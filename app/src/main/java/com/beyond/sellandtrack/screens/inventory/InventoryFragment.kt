package com.beyond.sellandtrack.screens.inventory

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.beyond.sellandtrack.R
import com.beyond.sellandtrack.databinding.FragmentAddProductBinding.inflate
import com.beyond.sellandtrack.databinding.FragmentInventoryBinding
import kotlinx.android.synthetic.main.add_inventory_form.view.*
import kotlinx.android.synthetic.main.fragment_welcome.*

/**
 * A simple [Fragment] subclass.
 */
class InventoryFragment : Fragment() {
    private lateinit var inventoryViewModel: InventoryViewModel
    private lateinit var binding : FragmentInventoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,R.layout.fragment_inventory,container,false
        )
        inventoryViewModel = ViewModelProvider(this).get(InventoryViewModel::class.java)
        inventoryViewModel.getProducts()

        //binding with adapter for recycler view
        val recyclerView = binding.recyclerView
        val adapter = InventoryAdapter(activity)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)


        // observe change in product list
        inventoryViewModel.inventory.observe(viewLifecycleOwner, Observer {inventory->
            inventory?.let { adapter.setInventory(inventory) }
        })
        binding.floatingActionButton.setOnClickListener {
            addInventoryFormDialog()
        }



        return binding.root
    }

    private fun addInventoryFormDialog() {
        val addInventoryForm = LayoutInflater.from(activity).inflate(R.layout.add_inventory_form,null)
        val dialog = activity?.let { it1 -> AlertDialog.Builder(it1) }
        val item_code= addInventoryForm.findViewById<EditText>(R.id.item_code_edittext)
        val item_qty = addInventoryForm.findViewById<EditText>(R.id.item_qty_edittext)
        val btn = addInventoryForm.findViewById<Button>(R.id.add_button)
        dialog?.setView(addInventoryForm)
        val alertDialog = dialog?.show()


        btn.setOnClickListener {
            alertDialog?.dismiss()
            val code= item_code.text.toString()
            val qty= item_qty.text.toString()
            inventoryViewModel.changeQuantity(code,qty)
        }

    }


}

