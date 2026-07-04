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
    private val onRemove: (Favorite) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavViewHolder>() {

    inner class FavViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(fav: Favorite) {
            val rupiah = NumberFormat.getNumberInstance(Locale("in", "ID"))
            binding.viewColor.setBackgroundColor(Color.parseColor(fav.colorHex))
            binding.tvName.text = fav.name
            binding.tvPrice.text = "Rp${rupiah.format(fav.price)}"
            binding.btnRemoveFav.setOnClickListener { onRemove(fav) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) = holder.bind(items[position])
    override fun getItemCount() = items.size
}
