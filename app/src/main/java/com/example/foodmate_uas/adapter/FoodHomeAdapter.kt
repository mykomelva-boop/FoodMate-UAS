package com.example.foodmate_uas.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmate_uas.databinding.ItemFoodHomeBinding
import com.example.foodmate_uas.model.Food
import java.text.NumberFormat
import java.util.Locale

class FoodHomeAdapter(
    private val items: List<Food>,
    private val onClick: (Food) -> Unit
) : RecyclerView.Adapter<FoodHomeAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemFoodHomeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFoodHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = items[position]
        val rupiah = NumberFormat.getNumberInstance(Locale("in", "ID")).format(food.price)
        holder.binding.tvName.text = food.name
        holder.binding.tvPrice.text = "Rp$rupiah"
        holder.binding.tvRating.text = "⭐ ${food.rating}"
        if (food.imageRes != 0) holder.binding.ivFood.setImageResource(food.imageRes)
        else holder.binding.ivFood.setBackgroundColor(Color.parseColor(food.colorHex))
        holder.itemView.setOnClickListener { onClick(food) }
    }

    override fun getItemCount() = items.size
}