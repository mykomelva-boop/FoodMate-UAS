package com.example.foodmate_uas.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmate_uas.databinding.ItemCategoryBinding
import com.example.foodmate_uas.model.Category

class CategoryAdapter(
    private val items: List<Category>,
    private val onClick: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var selectedPosition = 0

    inner class ViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = items[position]
        holder.binding.tvCategoryName.text = category.name
        holder.binding.ivCategoryIcon.setImageResource(category.iconRes)

        val isSelected = position == selectedPosition
        holder.binding.tvCategoryName.setTextColor(
            if (isSelected) Color.parseColor("#FF6B35") else Color.parseColor("#212121")
        )
        holder.binding.cardCategory.cardElevation = if (isSelected) 4f else 0f

        holder.itemView.setOnClickListener {
            val previous = selectedPosition
            selectedPosition = position
            notifyItemChanged(previous)
            notifyItemChanged(position)
            onClick(category)
        }
    }

    override fun getItemCount() = items.size
}