package com.example.foodmate_uas.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmate_uas.activity.DetailActivity
import com.example.foodmate_uas.adapter.FoodHomeAdapter
import com.example.foodmate_uas.adapter.PromoAdapter
import com.example.foodmate_uas.databinding.FragmentHomeBinding
import com.example.foodmate_uas.model.Promo
import com.example.foodmate_uas.network.PromoService
import com.example.foodmate_uas.repository.FoodRepository

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val popularFood = FoodRepository.allFood.sortedByDescending { it.rating }.take(5)
        binding.rvPopular.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPopular.adapter = FoodHomeAdapter(popularFood) { food ->
            startActivity(Intent(requireContext(), DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_FOOD_ID, food.id))
        }

        binding.rvPromo.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.tvPromoLoading.visibility = View.VISIBLE
        PromoService.fetchPromos(requireContext(), object : PromoService.Callback {
            override fun onSuccess(promos: List<Promo>) {
                if (_binding == null) return
                binding.tvPromoLoading.visibility = View.GONE
                binding.rvPromo.adapter = PromoAdapter(promos)
            }
            override fun onError(message: String) {
                if (_binding == null) return
                binding.tvPromoLoading.text = "Gagal memuat promo: $message"
            }
        })
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}