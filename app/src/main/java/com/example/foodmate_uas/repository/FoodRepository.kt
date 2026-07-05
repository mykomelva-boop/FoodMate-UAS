package com.example.foodmate_uas.repository

import android.content.Context
import com.example.foodmate_uas.model.Food
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.BufferedReader
import java.io.InputStreamReader

private data class FoodJson(
    val id: Int,
    val name: String,
    val category: String,
    val price: Int,
    val rating: Float,
    val description: String,
    val colorHex: String,
    @SerializedName("imageName") val imageName: String
)

object FoodRepository {
    var allFood: List<Food> = emptyList()
        private set

    private var isLoaded = false

    fun loadFromJson(context: Context) {
        if (isLoaded) return

        val jsonString = context.assets.open("foods.json").use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).readText()
        }

        val gson = Gson()
        val foodJsonArray = gson.fromJson(jsonString, Array<FoodJson>::class.java)

        allFood = foodJsonArray.map { item ->
            val resId = context.resources.getIdentifier(
                item.imageName, "drawable", context.packageName
            )
            Food(
                id = item.id,
                name = item.name,
                category = item.category,
                price = item.price,
                rating = item.rating,
                description = item.description,
                colorHex = item.colorHex,
                imageRes = resId
            )
        }
        isLoaded = true
    }

    fun getById(id: Int): Food? = allFood.find { it.id == id }
}