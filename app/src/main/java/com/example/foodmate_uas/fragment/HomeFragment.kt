package com.example.foodmate_uas.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmate_uas.R
import com.example.foodmate_uas.activity.DetailActivity
import com.example.foodmate_uas.adapter.CategoryAdapter
import com.example.foodmate_uas.adapter.FoodHomeAdapter
import com.example.foodmate_uas.adapter.PromoAdapter
import com.example.foodmate_uas.databinding.FragmentHomeBinding
import com.example.foodmate_uas.model.Category
import com.example.foodmate_uas.model.Promo
import com.example.foodmate_uas.network.PromoService
import com.example.foodmate_uas.repository.FoodRepository

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var popularAdapter: FoodHomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularAdapter = FoodHomeAdapter(FoodRepository.allFood.sortedByDescending { it.rating }.take(5)) { food ->
            startActivity(Intent(requireContext(), DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_FOOD_ID, food.id))
        }
        binding.rvPopular.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPopular.adapter = popularAdapter

        val categories = listOf(
            Category("Semua", R.drawable.cat_all, "Semua"),
            Category("Berat", R.drawable.cat_heavy, "Makanan Berat"),
            Category("Minuman", R.drawable.cat_drink, "Minuman"),
            Category("Camilan", R.drawable.cat_snack, "Camilan"),
            Category("Makanan Penutup", R.drawable.cat_dessert, "Dessert"),
            Category("Roti", R.drawable.cat_bakery, "Bakery")
        )

        binding.rvCategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategory.adapter = CategoryAdapter(categories) { category ->
            filterByCategory(category.filterKey)
        }

        binding.etSearchHome.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val keyword = s.toString().trim()
                val filtered = if (keyword.isEmpty()) FoodRepository.allFood
                else FoodRepository.allFood.filter { it.name.contains(keyword, true) }
                popularAdapter.updateData(filtered.take(5))
            }
            override fun afterTextChanged(s: Editable?) {}
        })

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

        binding.layoutLocation.setOnClickListener {
            val locations = arrayOf(
                "Jl. Sudirman No.1, Jakarta",
                "Jl. Thamrin No.5, Jakarta",
                "Jl. Gatot Subroto No.10, Jakarta"
            )
            android.app.AlertDialog.Builder(requireContext())
                .setTitle("Pilih Lokasi")
                .setItems(locations) { _, index ->
                    binding.tvLocation.text = locations[index]
                }
                .show()
        }

        binding.ivNotification.setOnClickListener {
            android.widget.Toast.makeText(requireContext(), "Belum ada notifikasi baru", android.widget.Toast.LENGTH_SHORT).show()
        }
    }

    private fun filterByCategory(categoryName: String) {
        val filtered = if (categoryName == "Semua") FoodRepository.allFood
        else FoodRepository.allFood.filter { it.category.contains(categoryName, true) }
        popularAdapter.updateData(filtered)
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}