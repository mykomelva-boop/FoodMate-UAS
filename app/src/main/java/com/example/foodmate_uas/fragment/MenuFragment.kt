package com.example.foodmate_uas.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodmate_uas.activity.DetailActivity
import com.example.foodmate_uas.adapter.FoodGridAdapter
import com.example.foodmate_uas.databinding.FragmentMenuBinding
import com.example.foodmate_uas.repository.FoodRepository

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FoodGridAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FoodGridAdapter(FoodRepository.allFood) { food ->
            startActivity(Intent(requireContext(), DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_FOOD_ID, food.id))
        }
        binding.rvMenu.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvMenu.adapter = adapter

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val keyword = s.toString().trim()
                val filtered = if (keyword.isEmpty()) FoodRepository.allFood
                else FoodRepository.allFood.filter { it.name.contains(keyword, true) || it.category.contains(keyword, true) }
                adapter.updateData(filtered)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
