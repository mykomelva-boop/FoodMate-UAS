kotlin
package com.foodmate.uas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.foodmate.uas.databinding.ItemPromoBinding
import com.foodmate.uas.model.Promo

class PromoAdapter(private val items: List<Promo>) : RecyclerView.Adapter<PromoAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemPromoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPromoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val promo = items[position]
        holder.binding.tvTitle.text = promo.title
        holder.binding.tvDescription.text = promo.description
        holder.binding.tvDiscount.text = promo.discount
    }

    override fun getItemCount() = items.size
}
