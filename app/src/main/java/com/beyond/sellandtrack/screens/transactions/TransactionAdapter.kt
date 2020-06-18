package com.beyond.sellandtrack.screens.transactions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beyond.sellandtrack.R
import com.beyond.sellandtrack.data.dataClasses.Product
import com.google.common.primitives.UnsignedBytes.toInt

class TransactionAdapter internal constructor(
        context: Context?
) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>(){

    private val inflater : LayoutInflater = LayoutInflater.from(context)
    private var products = emptyList<Product>()

    inner class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemNameTextView =itemView.findViewById<TextView>(R.id.item_name)
        val itemQtyTextView =itemView.findViewById<TextView>(R.id.item_qty)
        val itemCodeTextView = itemView.findViewById<TextView>(R.id.item_code)
        val itemPriceTextView = itemView.findViewById<TextView>(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val itemView=  inflater.inflate(R.layout.transaction_item,parent,false)
        return TransactionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val current = products[position]
        holder.itemNameTextView.text = current.name
        holder.itemCodeTextView.text=  current.item_code
        holder.itemQtyTextView.text = "Purchase Quantity: "+current.quantity
        holder.itemPriceTextView.text = current.price+" pkr"
    }

    internal fun addToTransaction(prod: List<Product>){
        this.products = prod
        notifyDataSetChanged()
    }

    override fun getItemCount() = products.size

}