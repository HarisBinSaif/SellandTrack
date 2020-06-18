package com.beyond.sellandtrack.screens.transactions

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.beyond.sellandtrack.R
import com.beyond.sellandtrack.data.dataClasses.Product
import com.beyond.sellandtrack.databinding.FragmentTransactionBinding
import com.beyond.sellandtrack.screens.inventory.InventoryViewModel

/**
 * A simple [Fragment] subclass.
 */
class TransactionFragment : Fragment() {
    private lateinit var adapter: TransactionAdapter
    private var qty : String?=null
    private var code : String?=null
    private val productList: MutableList<Product> = mutableListOf()
    private lateinit var transactionViewModel: TransactionViewModel
    private lateinit var binding: FragmentTransactionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_transaction, container, false
        )

        transactionViewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)

        val recyclerView = binding.recyclerViewTransaction
        adapter = TransactionAdapter(activity)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        transactionViewModel.getProductInformation()
        binding.addItemBtn.setOnClickListener {
            addItemForm()
            transactionViewModel.product.observe(viewLifecycleOwner, Observer { product->
                val pro : Product = product
                if(pro.item_code == code){
                    productList.add(pro)
                }
                adapter.addToTransaction(productList)
            })


        }
        return binding.root
    }

    private fun addItemForm() {
        val addItemForm = LayoutInflater.from(activity).inflate(R.layout.add_item_form, null)
        val dialog = activity?.let { it1 -> AlertDialog.Builder(it1) }
        val item_code = addItemForm.findViewById<EditText>(R.id.item_code_edittext)
        val item_qty = addItemForm.findViewById<EditText>(R.id.item_qty_edittext)
        val btn = addItemForm.findViewById<Button>(R.id.add_button_transaction)
        dialog?.setView(addItemForm)
        val alertDialog = dialog?.show()

        btn.setOnClickListener {
            alertDialog?.dismiss()
            code = item_code.text.toString()
            qty = item_qty.text.toString()
        }
    }
}
