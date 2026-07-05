package com.example.foodmate_uas.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmate_uas.databinding.ItemFavoriteBinding
import com.example.foodmate_uas.model.Favorite
import com.example.foodmate_uas.repository.FoodRepository
import java.text.NumberFormat
import java.util.Locale

class FavoriteAdapter(
    private val items: List<Favorite>,
    private val onClick: (Favorite) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fav = items[position]
        val rupiah = NumberFormat.getNumberInstance(Locale("in", "ID")).format(fav.price)
        holder.binding.tvName.text = fav.name
        holder.binding.tvPrice.text = "Rp$rupiah"
        val imageRes = FoodRepository.getById(fav.foodId)?.imageRes ?: 0
        if (imageRes != 0) holder.binding.ivFood.setImageResource(imageRes)
        else holder.binding.ivFood.setBackgroundColor(Color.parseColor(fav.colorHex))
        holder.itemView.setOnClickListener { onClick(fav) }
    }

    override fun getItemCount() = items.size
}