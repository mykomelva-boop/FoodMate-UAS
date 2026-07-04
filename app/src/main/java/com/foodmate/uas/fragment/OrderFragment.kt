package com.foodmate.uas.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.foodmate.uas.adapter.OrderAdapter
import com.foodmate.uas.databinding.FragmentOrderBinding
import com.foodmate.uas.db.DatabaseHelper

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = DatabaseHelper(requireContext())
        binding.rvOrder.layoutManager = LinearLayoutManager(requireContext())
        loadOrders()
    }

    private fun loadOrders() {
        val orders = dbHelper.getAllOrders()
        if (orders.isEmpty()) {
            binding.tvEmpty.visibility = View.VISIBLE
            binding.rvOrder.visibility = View.GONE
        } else {
            binding.tvEmpty.visibility = View.GONE
            binding.rvOrder.visibility = View.VISIBLE
            binding.rvOrder.adapter = OrderAdapter(orders)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
