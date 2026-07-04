package com.foodmate.uas.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.foodmate.uas.activity.DetailActivity
import com.foodmate.uas.adapter.FoodAdapter
import com.foodmate.uas.adapter.PromoAdapter
import com.foodmate.uas.databinding.FragmentHomeBinding
import com.foodmate.uas.repository.FoodRepository
import com.foodmate.uas.util.SessionManager

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sessionManager = SessionManager(requireContext())

        // Greeting dinamis menggunakan nama dari profil
        val name = sessionManager.getDisplayName().ifEmpty { sessionManager.getUsername() }
        binding.tvGreeting.text = if (name.isNotEmpty()) "Halo, $name! 👋" else "Halo! 👋"

        // Setup promo banner (horizontal scroll)
        binding.rvPromo.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        binding.rvPromo.adapter = PromoAdapter(FoodRepository.getPromos())

        // Setup daftar makanan
        foodAdapter = FoodAdapter(FoodRepository.getAll()) { food ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_FOOD_ID, food.id)
            startActivity(intent)
        }
        binding.rvFood.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFood.adapter = foodAdapter

        // Fitur pencarian real-time
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val results = if (s.isNullOrBlank()) FoodRepository.getAll()
                else FoodRepository.search(s.toString())
                foodAdapter.updateList(results)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
