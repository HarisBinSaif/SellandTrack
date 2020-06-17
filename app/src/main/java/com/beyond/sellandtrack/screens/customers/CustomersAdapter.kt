package com.beyond.sellandtrack.screens.customers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beyond.sellandtrack.R
import com.beyond.sellandtrack.data.dataClasses.Customer
import com.beyond.sellandtrack.data.dataClasses.Product
import com.beyond.sellandtrack.screens.inventory.InventoryAdapter

class CustomersAdapter internal constructor(
        context: Context?
) : RecyclerView.Adapter<CustomersAdapter.CustomerViewHolder>(){

    private val inflater : LayoutInflater = LayoutInflater.from(context)
    private var customers = emptyList<Customer>()

    inner class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val customerNameTextView =itemView.findViewById<TextView>(R.id.cust_name)
        val customerIDTextView =itemView.findViewById<TextView>(R.id.cust_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val itemView=  inflater.inflate(R.layout.customer_item,parent,false)
        return CustomerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val current = customers[position]
        holder.customerNameTextView.text = current.name
        holder.customerIDTextView.text=  current.number
    }

    internal fun setInventory(customers: List<Customer>){
        this.customers = customers
        notifyDataSetChanged()
    }

    override fun getItemCount() = customers.size
}