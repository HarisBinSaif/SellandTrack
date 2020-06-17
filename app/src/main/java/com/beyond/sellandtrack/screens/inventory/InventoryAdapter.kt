package com.beyond.sellandtrack.screens.inventory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beyond.sellandtrack.R
import com.beyond.sellandtrack.data.dataClasses.Product

class InventoryAdapter internal constructor(
    context: Context?
) : RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder>(){

    private val inflater : LayoutInflater = LayoutInflater.from(context)
    private var inventory = emptyList<Product>()

    inner class InventoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemNameTextView =itemView.findViewById<TextView>(R.id.item_name)
        val itemQtyTextView =itemView.findViewById<TextView>(R.id.item_qty)
        val itemCodeTextView = itemView.findViewById<TextView>(R.id.item_code)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        val itemView=  inflater.inflate(R.layout.inventory_item,parent,false)
        return InventoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        val current = inventory[position]
        holder.itemNameTextView.text = current.name
        holder.itemCodeTextView.text=  current.item_code
        holder.itemQtyTextView.text = "Available Quantity: "+current.quantity
    }

    internal fun setInventory(prod: List<Product>){
        this.inventory = prod
        notifyDataSetChanged()
    }

    override fun getItemCount() = inventory .size

}