kotlin
package com.foodmate.uas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.foodmate.uas.databinding.ItemOrderBinding
import com.foodmate.uas.model.Order
import java.text.NumberFormat
import java.util.Locale

class OrderAdapter(private val items: List<Order>) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = items[position]
        val rupiah = NumberFormat.getNumberInstance(Locale("in", "ID")).format(order.totalPrice)
        holder.binding.tvItems.text = order.itemsSummary
        holder.binding.tvTotal.text = "Rp$rupiah"
        holder.binding.tvDate.text = order.date
    }

    override fun getItemCount() = items.size
}

