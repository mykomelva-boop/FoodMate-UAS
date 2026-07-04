package com.foodmate.uas.repository

import com.foodmate.uas.model.Food
import com.foodmate.uas.model.Promo

object FoodRepository {

    private val foods = listOf(
        Food(1, "Nasi Goreng Spesial", "Nasi", 25000, 4.8f,
            "Nasi goreng dengan telur, ayam, dan bumbu rempah khas yang menggugah selera.", "#FF6B35"),
        Food(2, "Mie Ayam Bakso", "Mie", 20000, 4.5f,
            "Mie kenyal dengan topping ayam cincang dan bakso sapi lembut.", "#E91E63"),
        Food(3, "Ayam Bakar Madu", "Ayam", 35000, 4.9f,
            "Ayam bakar dengan olesan madu dan kecap, disajikan dengan lalapan segar.", "#FF9800"),
        Food(4, "Soto Ayam", "Soto", 18000, 4.6f,
            "Soto kuning khas dengan suwiran ayam, tauge, dan perkedel kentang.", "#FFC107"),
        Food(5, "Gado-Gado", "Sayuran", 15000, 4.4f,
            "Sayuran rebus dengan saus kacang spesial yang kaya rasa.", "#4CAF50"),
        Food(6, "Rendang Sapi", "Daging", 45000, 4.9f,
            "Rendang daging sapi empuk dengan bumbu rempah Padang yang kaya.", "#795548"),
        Food(7, "Bakso Malang", "Bakso", 22000, 4.7f,
            "Bakso kenyal dalam kuah kaldu segar dengan berbagai topping.", "#2196F3"),
        Food(8, "Es Teh Manis", "Minuman", 8000, 4.3f,
            "Es teh manis segar yang menyegarkan, cocok menemani makanan apapun.", "#00BCD4"),
        Food(9, "Pisang Goreng", "Snack", 12000, 4.5f,
            "Pisang goreng renyah dengan taburan gula halus dan keju.", "#FFEB3B"),
        Food(10, "Jus Alpukat", "Minuman", 15000, 4.6f,
            "Jus alpukat creamy dengan susu kental manis, padat dan menyegarkan.", "#66BB6A")
    )

    private val promos = listOf(
        Promo("Promo Hari Ini", "Diskon 20% untuk semua menu nasi", "20%"),
        Promo("Spesial Weekend", "Gratis minuman untuk setiap pembelian di atas Rp50.000", "FREE"),
        Promo("Member Baru", "Diskon 15% untuk order pertama Anda", "15%")
    )

    fun getAll(): List<Food> = foods

    fun getById(id: Int): Food? = foods.find { it.id == id }

    fun getByCategory(category: String): List<Food> =
        if (category == "Semua") foods else foods.filter { it.category == category }

    fun search(query: String): List<Food> =
        foods.filter { it.name.contains(query, ignoreCase = true) || it.category.contains(query, ignoreCase = true) }

    fun getPromos(): List<Promo> = promos

    fun getCategories(): List<String> = listOf("Semua") + foods.map { it.category }.distinct()
}
