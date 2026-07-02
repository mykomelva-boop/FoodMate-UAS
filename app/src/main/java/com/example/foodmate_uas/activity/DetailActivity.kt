package com.foodmate.uas.activity

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.foodmate.uas.R
import com.foodmate.uas.databinding.ActivityDetailBinding
import com.foodmate.uas.db.DatabaseHelper
import com.foodmate.uas.model.Favorite
import com.foodmate.uas.repository.FoodRepository
import com.foodmate.uas.util.CartManager
import java.text.NumberFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {
    companion object { const val EXTRA_FOOD_ID = "extra_food_id" }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var dbHelper: DatabaseHelper
    private var qty = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = DatabaseHelper(this)

        val food = FoodRepository.getById(intent.getIntExtra(EXTRA_FOOD_ID, -1))
        if (food == null) { finish(); return }

        val rupiah = NumberFormat.getNumberInstance(Locale("in", "ID")).format(food.price)
        binding.tvName.text = food.name
        binding.tvCategory.text = food.category
        binding.tvDescription.text = food.description
        binding.tvPrice.text = "Rp$rupiah"
        binding.viewColor.setBackgroundColor(Color.parseColor(food.colorHex))
        updateFavoriteIcon(dbHelper.isFavorite(food.id))

        binding.btnBack.setOnClickListener { finish() }
        binding.btnFavorite.setOnClickListener {
            if (dbHelper.isFavorite(food.id)) {
                dbHelper.removeFavorite(food.id); updateFavoriteIcon(false)
            } else {
                dbHelper.addFavorite(Favorite(food.id, food.name, food.price, food.colorHex))
                updateFavoriteIcon(true)
            }
        }
        binding.btnMinus.setOnClickListener { if (qty > 1) { qty--; binding.tvQty.text = qty.toString() } }
        binding.btnPlus.setOnClickListener { qty++; binding.tvQty.text = qty.toString() }
        binding.btnAddToCart.setOnClickListener {
            CartManager.addToCart(food, qty)
            Toast.makeText(this, "${food.name} ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun updateFavoriteIcon(isFav: Boolean) {
        binding.btnFavorite.setImageResource(if (isFav) R.drawable.ic_fav_filled else R.drawable.ic_fav_border)
    }
}