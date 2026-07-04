package com.foodmate.uas.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.foodmate.uas.databinding.ItemFoodBinding
import com.foodmate.uas.model.Food
import java.text.NumberFormat
import java.util.Locale

class FoodAdapter(
    private var items: List<Food>,
    private val onClick: (Food) -> Unit
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(private val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(food: Food) {
            val rupiah = NumberFormat.getNumberInstance(Locale("in", "ID"))
            binding.viewColor.setBackgroundColor(Color.parseColor(food.colorHex))
            binding.tvName.text = food.name
            binding.tvCategory.text = food.category
            binding.tvPrice.text = "Rp${rupiah.format(food.price)}"
            binding.tvRating.text = food.rating.toString()
            binding.root.setOnClickListener { onClick(food) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) = holder.bind(items[position])
    override fun getItemCount() = items.size

    fun updateList(newList: List<Food>) {
        items = newList
        notifyDataSetChanged()
    }
}
