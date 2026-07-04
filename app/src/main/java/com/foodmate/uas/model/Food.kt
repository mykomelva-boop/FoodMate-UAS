package com.foodmate.uas.model

data class Food(
    val id: Int, val name: String, val category: String, val price: Int,
    val rating: Float, val description: String, val colorHex: String
)
