package com.example.foodmate_uas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmate_uas.databinding.ItemCartBinding
import com.example.foodmate_uas.model.CartItem
import java.text.NumberFormat
import java.util.Locale
import android.graphics.Color

class CartAdapter(
    private val items: List<CartItem>,
    private val onRemove: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val rupiah = NumberFormat.getNumberInstance(Locale("in", "ID")).format(item.food.price * item.qty)
        holder.binding.tvName.text = item.food.name
        holder.binding.tvQty.text = "x${item.qty}"
        holder.binding.tvSubtotal.text = "Rp$rupiah"
        if (item.food.imageRes != 0) holder.binding.ivFood.setImageResource(item.food.imageRes)
        else holder.binding.ivFood.setBackgroundColor(Color.parseColor(item.food.colorHex))
        holder.binding.btnRemove.setOnClickListener { onRemove(item) }
    }

    override fun getItemCount() = items.size
}
