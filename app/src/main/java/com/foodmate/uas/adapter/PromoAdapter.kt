package com.foodmate.uas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.foodmate.uas.databinding.ItemPromoBinding
import com.foodmate.uas.model.Promo

class PromoAdapter(
    private val items: List<Promo>
) : RecyclerView.Adapter<PromoAdapter.PromoViewHolder>() {

    inner class PromoViewHolder(private val binding: ItemPromoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(promo: Promo) {
            binding.tvPromoTitle.text = promo.title
            binding.tvPromoDescription.text = promo.description
            binding.tvDiscount.text = promo.discount
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        val binding = ItemPromoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PromoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) = holder.bind(items[position])
    override fun getItemCount() = items.size
}
