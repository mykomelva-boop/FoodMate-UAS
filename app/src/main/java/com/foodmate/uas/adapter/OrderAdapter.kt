package com.foodmate.uas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.foodmate.uas.databinding.ItemOrderBinding
import com.foodmate.uas.model.Order
import java.text.NumberFormat
import java.util.Locale

class OrderAdapter(
    private val items: List<Order>
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(private val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(order: Order) {
            val rupiah = NumberFormat.getNumberInstance(Locale("in", "ID"))
            binding.tvOrderItems.text = order.itemsSummary
            binding.tvOrderTotal.text = "Rp${rupiah.format(order.totalPrice)}"
            binding.tvOrderDate.text = order.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) = holder.bind(items[position])
    override fun getItemCount() = items.size
}
