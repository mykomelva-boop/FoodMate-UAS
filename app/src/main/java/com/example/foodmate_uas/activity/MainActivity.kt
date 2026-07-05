package com.example.foodmate_uas.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.foodmate_uas.R
import com.example.foodmate_uas.databinding.ActivityMainBinding
import com.example.foodmate_uas.fragment.CartFragment
import com.example.foodmate_uas.fragment.HomeFragment
import com.example.foodmate_uas.fragment.MenuFragment
import com.example.foodmate_uas.fragment.ProfileFragment
import com.example.foodmate_uas.repository.FoodRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        FoodRepository.loadFromJson(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) loadFragment(HomeFragment())

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navHome -> loadFragment(HomeFragment())
                R.id.navMenu -> loadFragment(MenuFragment())
                R.id.navCart -> loadFragment(CartFragment())
                R.id.navProfile -> loadFragment(ProfileFragment())
            }
            true
        }
    }

    private fun loadFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }

    fun selectBottomNavItem(itemId: Int) { binding.bottomNav.selectedItemId = itemId }
}