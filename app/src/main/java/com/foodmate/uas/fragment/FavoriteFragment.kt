package com.foodmate.uas.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.foodmate.uas.adapter.FavoriteAdapter
import com.foodmate.uas.databinding.FragmentFavoriteBinding
import com.foodmate.uas.db.DatabaseHelper

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = DatabaseHelper(requireContext())
        binding.rvFavorite.layoutManager = LinearLayoutManager(requireContext())
        loadFavorites()
    }

    private fun loadFavorites() {
        val favorites = dbHelper.getAllFavorites()
        if (favorites.isEmpty()) {
            binding.tvEmpty.visibility = View.VISIBLE
            binding.rvFavorite.visibility = View.GONE
        } else {
            binding.tvEmpty.visibility = View.GONE
            binding.rvFavorite.visibility = View.VISIBLE
            binding.rvFavorite.adapter = FavoriteAdapter(favorites) { fav ->
                dbHelper.removeFavorite(fav.foodId)
                loadFavorites()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
