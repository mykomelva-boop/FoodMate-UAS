package com.example.foodmate_uas.repository

import com.example.foodmate_uas.R
import com.example.foodmate_uas.model.Food

object FoodRepository {
    val allFood = listOf(
        Food(1, "Nasi Goreng Spesial", "Makanan Berat", 22000, 4.8f, "Nasi goreng dengan telur, ayam suwir, dan acar segar.", "#FF6B35", R.drawable.nasi_goreng),
        Food(2, "Ayam Geprek Sambal", "Makanan Berat", 20000, 4.7f, "Ayam crispy digeprek dengan sambal bawang pedas.", "#E4551E", R.drawable.ayam_geprek),
        Food(3, "Mie Ayam Bakso", "Makanan Berat", 18000, 4.6f, "Mie ayam kuah gurih lengkap dengan bakso sapi.", "#2EC4B6", R.drawable.mie_ayam_baso),
        Food(4, "Sate Ayam Madura", "Makanan Berat", 25000, 4.9f, "Sate ayam bumbu kacang khas Madura, 10 tusuk.", "#FF9F1C", R.drawable.sate_ayam),
        Food(5, "Es Teh Manis", "Minuman", 5000, 4.5f, "Teh manis dingin segar, cocok buat cuaca panas.", "#4CC9F0", R.drawable.es_teh),
        Food(6, "Es Jeruk Peras", "Minuman", 8000, 4.6f, "Jeruk peras asli tanpa pengawet.", "#F9C74F", R.drawable.es_jeruk),
        Food(7, "Risoles Mayo", "Camilan", 6000, 4.4f, "Risoles isi ragout ayam dengan topping mayones.", "#F3722C", R.drawable.risoles),
        Food(8, "Pisang Goreng Coklat", "Camilan", 7000, 4.3f, "Pisang goreng crispy dengan lelehan coklat.", "#90BE6D", R.drawable.pisang_goreng)
    )
    fun getById(id: Int): Food? = allFood.find { it.id == id }
}