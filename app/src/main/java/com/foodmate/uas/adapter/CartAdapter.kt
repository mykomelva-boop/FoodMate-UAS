package com.foodmate.uas.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.foodmate.uas.databinding.ItemCartBinding
import com.foodmate.uas.model.CartItem
import java.text.NumberFormat
import java.util.Locale

class CartAdapter(
    private val items: List<CartItem>,
    private val onRemove: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CartItem) {
            val rupiah = NumberFormat.getNumberInstance(Locale("in", "ID"))
            binding.viewColor.setBackgroundColor(Color.parseColor(item.food.colorHex))
            binding.tvName.text = item.food.name
            binding.tvQty.text = "x${item.qty}"
            binding.tvSubtotal.text = "Rp${rupiah.format(item.food.price * item.qty)}"
            binding.btnRemove.setOnClickListener { onRemove(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) = holder.bind(items[position])
    override fun getItemCount() = items.size
}
