package com.foodmate.uas.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.foodmate.uas.activity.LoginActivity
import com.foodmate.uas.databinding.FragmentProfileBinding
import com.foodmate.uas.db.DatabaseHelper
import com.foodmate.uas.util.SessionManager

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var sessionManager: SessionManager
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sessionManager = SessionManager(requireContext())
        dbHelper = DatabaseHelper(requireContext())

        loadProfileData()
        setupListeners()
    }

    private fun loadProfileData() {
        val displayName = sessionManager.getDisplayName().ifEmpty { sessionManager.getUsername() }

        // Info panel atas
        binding.tvProfileName.text = displayName
        binding.tvProfileUsername.text = "@${sessionManager.getUsername()}"

        // Avatar initial (huruf pertama dari nama)
        binding.tvAvatarInitial.text = displayName.firstOrNull()?.uppercaseChar()?.toString() ?: "?"

        // Hitung statistik
        val totalOrders = dbHelper.getAllOrders().size
        val totalFavorites = dbHelper.getAllFavorites().size
        binding.tvTotalOrders.text = totalOrders.toString()
        binding.tvTotalFavorites.text = totalFavorites.toString()

        // Isi form edit profil
        binding.etDisplayName.setText(sessionManager.getDisplayName())
        binding.etEmail.setText(sessionManager.getEmail())
        binding.etPhone.setText(sessionManager.getPhone())
        binding.etAddress.setText(sessionManager.getAddress())
    }

    private fun setupListeners() {
        binding.btnSaveProfile.setOnClickListener {
            saveProfile()
        }

        binding.btnLogout.setOnClickListener {
            showLogoutConfirmation()
        }
    }

    private fun saveProfile() {
        val displayName = binding.etDisplayName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val address = binding.etAddress.text.toString().trim()

        if (displayName.isEmpty()) {
            binding.tilDisplayName.error = "Nama tidak boleh kosong"
            return
        }
        binding.tilDisplayName.error = null

        // Validasi email sederhana
        if (email.isNotEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Format email tidak valid"
            return
        }
        binding.tilEmail.error = null

        sessionManager.updateProfile(displayName, email, phone, address)

        // Refresh nama dan initial di header
        binding.tvProfileName.text = displayName
        binding.tvAvatarInitial.text = displayName.firstOrNull()?.uppercaseChar()?.toString() ?: "?"

        Toast.makeText(requireContext(), "Profil berhasil disimpan", Toast.LENGTH_SHORT).show()
    }

    private fun showLogoutConfirmation() {
        AlertDialog.Builder(requireContext())
            .setTitle("Keluar")
            .setMessage("Apakah kamu yakin ingin keluar?")
            .setPositiveButton("Keluar") { _, _ ->
                sessionManager.logout()
                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
