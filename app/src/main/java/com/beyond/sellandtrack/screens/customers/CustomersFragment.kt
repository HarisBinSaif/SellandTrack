package com.beyond.sellandtrack.screens.customers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.beyond.sellandtrack.R
import com.beyond.sellandtrack.data.dataClasses.Customer
import com.beyond.sellandtrack.databinding.FragmentCustomersBinding
import kotlinx.android.synthetic.main.customer_item.*

/**
 * A simple [Fragment] subclass.
 */
class CustomersFragment : Fragment(), OnItemClickListener {

    private lateinit var binding : FragmentCustomersBinding
    private lateinit var customersViewModel: CustomersViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentCustomersBinding>(
                inflater,R.layout.fragment_customers,container,false
        )

        customersViewModel = ViewModelProvider(this).get(CustomersViewModel::class.java)
        customersViewModel.getCustomers()
        var recyclerView=  binding.recyclerView
        val adapter=  CustomersAdapter(activity,this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)


        customersViewModel.customers.observe(viewLifecycleOwner, Observer {customers->
            customers?.let { adapter.setInventory(customers) }
        })

        binding.floatingActionButton.setOnClickListener {
            addCustomerDialog()
        }



        return binding.root
    }

    private fun addCustomerDialog() {
        val addCustomerForm = LayoutInflater.from(activity).inflate(R.layout.add_customer_form,null)
        val dialog = activity?.let { it1 -> AlertDialog.Builder(it1) }
        val number= addCustomerForm.findViewById<EditText>(R.id.cust_number_edittext)
        val name = addCustomerForm.findViewById<EditText>(R.id.cust_name_edittext)
        val btn = addCustomerForm.findViewById<Button>(R.id.add_button)
        dialog?.setView(addCustomerForm)
        val alertDialog = dialog?.show()
        btn.setOnClickListener {
            alertDialog?.dismiss()
            val number= number.text.toString()
            val name= name.text.toString()
            customersViewModel.addCustomer(number,name)
        }
    }

    override fun onItemClicked(customer: Customer) {
        deleteCustomerDialog(customer)
    }

    private fun deleteCustomerDialog(customer: Customer) {
        val deleteCustomerDialog = LayoutInflater.from(activity).inflate(R.layout.delete_customer_dialog,null)

        val dialog = activity?.let { it1 -> AlertDialog.Builder(it1) }
        val cancelButton = deleteCustomerDialog.findViewById<Button>(R.id.cancel_button)
        val deleteButton = deleteCustomerDialog.findViewById<Button>(R.id.delete_button)
        val textTitle = deleteCustomerDialog.findViewById<TextView>(R.id.title_text)
        textTitle.text = "Delete "+customer.name+"?"
        dialog?.setView(deleteCustomerDialog)
        val alertDialog = dialog?.show()
        cancelButton.setOnClickListener {
            alertDialog?.dismiss()
        }

        deleteButton.setOnClickListener {
            customersViewModel.deleteCustomer(customer.number)
            customersViewModel.getCustomers()
            alertDialog?.dismiss()
        }

    }

}
