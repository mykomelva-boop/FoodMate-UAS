package com.foodmate.uas.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.foodmate.uas.R
import com.foodmate.uas.databinding.ActivityMainBinding
import com.foodmate.uas.fragment.CartFragment
import com.foodmate.uas.fragment.FavoriteFragment
import com.foodmate.uas.fragment.HomeFragment
import com.foodmate.uas.fragment.OrderFragment
import com.foodmate.uas.fragment.ProfileFragment
import com.foodmate.uas.util.SessionManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sessionManager: SessionManager

    private val orderReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            // Refresh badge keranjang atau notifikasi jika diperlukan
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        // Register broadcast receiver
        val filter = IntentFilter("com.foodmate.uas.ORDER_SUCCESS")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(orderReceiver, filter, RECEIVER_NOT_EXPORTED)
        } else {
            @Suppress("UnspecifiedRegisterReceiverFlag")
            registerReceiver(orderReceiver, filter)
        }

        // Load fragment awal (Home)
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        binding.bottomNav.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.navHome -> HomeFragment()
                R.id.navCart -> CartFragment()
                R.id.navFavorite -> FavoriteFragment()
                R.id.navOrder -> OrderFragment()
                R.id.navProfile -> ProfileFragment()
                else -> HomeFragment()
            }
            loadFragment(fragment)
            true
        }
    }

    /**
     * Dipanggil dari fragment lain untuk berpindah tab secara programatik.
     * Contoh: setelah checkout, CartFragment mengarahkan kembali ke Home.
     */
    fun selectBottomNavItem(itemId: Int) {
        binding.bottomNav.selectedItemId = itemId
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(orderReceiver)
    }
}
