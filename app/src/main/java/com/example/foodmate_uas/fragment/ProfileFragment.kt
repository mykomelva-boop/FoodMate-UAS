package com.example.foodmate_uas.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmate_uas.adapter.FavoriteAdapter
import com.example.foodmate_uas.adapter.OrderAdapter
import com.example.foodmate_uas.databinding.FragmentProfileBinding
import com.example.foodmate_uas.db.DatabaseHelper

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var dbHelper: DatabaseHelper
    private var currentTab = TAB_RIWAYAT

    companion object { private const val TAB_RIWAYAT = 0; private const val TAB_FAVORIT = 1 }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = DatabaseHelper(requireContext())
        binding.rvProfile.layoutManager = LinearLayoutManager(requireContext())
        binding.btnTabRiwayat.setOnClickListener { switchTab(TAB_RIWAYAT) }
        binding.btnTabFavorit.setOnClickListener { switchTab(TAB_FAVORIT) }
        switchTab(TAB_RIWAYAT)
    }

    private fun switchTab(tab: Int) {
        currentTab = tab
        val active = Color.parseColor("#FF6B35"); val inactive = Color.parseColor("#777777")
        binding.btnTabRiwayat.backgroundTintList = ColorStateList.valueOf(if (tab == TAB_RIWAYAT) active else inactive)
        binding.btnTabFavorit.backgroundTintList = ColorStateList.valueOf(if (tab == TAB_FAVORIT) active else inactive)

        if (tab == TAB_RIWAYAT) {
            val orders = dbHelper.getAllOrders()
            binding.tvEmptyProfile.visibility = if (orders.isEmpty()) View.VISIBLE else View.GONE
            binding.tvEmptyProfile.text = "Belum ada riwayat pesanan"
            binding.rvProfile.adapter = OrderAdapter(orders)
        } else {
            val favorites = dbHelper.getAllFavorites()
            binding.tvEmptyProfile.visibility = if (favorites.isEmpty()) View.VISIBLE else View.GONE
            binding.tvEmptyProfile.text = "Belum ada makanan favorit"
            binding.rvProfile.adapter = FavoriteAdapter(favorites) { }
        }
    }

    override fun onResume() { super.onResume(); if (_binding != null) switchTab(currentTab) }
    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}