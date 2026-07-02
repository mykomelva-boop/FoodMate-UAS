kotlin
package com.foodmate.uas.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.foodmate.uas.databinding.ItemFavoriteBinding
import com.foodmate.uas.model.Favorite
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
        holder.binding.viewColor.setBackgroundColor(Color.parseColor(fav.colorHex))
        holder.itemView.setOnClickListener { onClick(fav) }
    }

    override fun getItemCount() = items.size
}
