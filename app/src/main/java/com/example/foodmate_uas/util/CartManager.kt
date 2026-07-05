package com.example.foodmate_uas.util

import com.example.foodmate_uas.model.CartItem
import com.example.foodmate_uas.model.Food

object CartManager {
    private val items = mutableListOf<CartItem>()

    fun addToCart(food: Food, qty: Int = 1) {
        val existing = items.find { it.food.id == food.id }
        if (existing != null) existing.qty += qty else items.add(CartItem(food, qty))
    }

    fun removeItem(foodId: Int) { items.removeAll { it.food.id == foodId } }
    fun getItems(): List<CartItem> = items.toList()
    fun getTotalPrice(): Int = items.sumOf { it.food.price * it.qty }
    fun clear() { items.clear() }
}