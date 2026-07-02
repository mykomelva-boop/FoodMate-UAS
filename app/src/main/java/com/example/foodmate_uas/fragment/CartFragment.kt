package com.foodmate.uas.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.foodmate.uas.R
import com.foodmate.uas.activity.MainActivity
import com.foodmate.uas.adapter.CartAdapter
import com.foodmate.uas.databinding.FragmentCartBinding
import com.foodmate.uas.db.DatabaseHelper
import com.foodmate.uas.util.CartManager
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = DatabaseHelper(requireContext())
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        renderCart()
        binding.btnCheckout.setOnClickListener { checkout() }
    }

    private fun renderCart() {
        val items = CartManager.getItems()
        if (items.isEmpty()) {
            binding.tvEmptyCart.visibility = View.VISIBLE
            binding.rvCart.visibility = View.GONE
            binding.layoutCheckout.visibility = View.GONE
        } else {
            binding.tvEmptyCart.visibility = View.GONE
            binding.rvCart.visibility = View.VISIBLE
            binding.layoutCheckout.visibility = View.VISIBLE
            binding.rvCart.adapter = CartAdapter(items) { cartItem -> CartManager.removeItem(cartItem.food.id); renderCart() }
            val rupiah = NumberFormat.getNumberInstance(Locale("in", "ID")).format(CartManager.getTotalPrice())
            binding.tvTotalPrice.text = "Rp$rupiah"
        }
    }

    private fun checkout() {
        val items = CartManager.getItems()
        if (items.isEmpty()) return

        val summary = items.joinToString(", ") { "${it.qty}x ${it.food.name}" }
        val total = CartManager.getTotalPrice()
        val date = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale("in", "ID")).format(Date())

        dbHelper.insertOrder(summary, total, date)
        CartManager.clear()
        renderCart()
        Toast.makeText(requireContext(), "Pesanan berhasil dibuat", Toast.LENGTH_SHORT).show()

        val broadcastIntent = Intent("com.foodmate.uas.ORDER_SUCCESS").apply {
            setPackage(requireContext().packageName)
            putExtra("total_price", total)
        }
        requireContext().sendBroadcast(broadcastIntent)
        (activity as? MainActivity)?.selectBottomNavItem(R.id.navHome)
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}