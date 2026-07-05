package com.example.foodmate_uas.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.foodmate_uas.R
import com.example.foodmate_uas.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoginForm()

        binding.btnTabLogin.setOnClickListener { showLoginForm() }
        binding.btnTabRegister.setOnClickListener { showRegisterForm() }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            if (email.isEmpty() || password.isEmpty()) {
                binding.tvError.visibility = View.VISIBLE
                return@setOnClickListener
            }
            binding.tvError.visibility = View.GONE
            goToMain()
        }

        binding.btnRegister.setOnClickListener {
            val name = binding.etRegName.text.toString().trim()
            val email = binding.etRegEmail.text.toString().trim()
            val phone = binding.etRegPhone.text.toString().trim()
            val password = binding.etRegPassword.text.toString().trim()
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                binding.tvRegError.visibility = View.VISIBLE
                return@setOnClickListener
            }
            binding.tvRegError.visibility = View.GONE
            goToMain()
        }
    }

    private fun showLoginForm() {
        binding.formLogin.visibility = View.VISIBLE
        binding.formRegister.visibility = View.GONE
        binding.btnTabLogin.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        binding.btnTabLogin.setTextColor(ContextCompat.getColor(this, R.color.black))
        binding.btnTabRegister.setBackgroundColor(ContextCompat.getColor(this, R.color.background))
        binding.btnTabRegister.setTextColor(ContextCompat.getColor(this, R.color.grey))
    }

    private fun showRegisterForm() {
        binding.formRegister.visibility = View.VISIBLE
        binding.formLogin.visibility = View.GONE
        binding.btnTabRegister.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        binding.btnTabRegister.setTextColor(ContextCompat.getColor(this, R.color.black))
        binding.btnTabLogin.setBackgroundColor(ContextCompat.getColor(this, R.color.background))
        binding.btnTabLogin.setTextColor(ContextCompat.getColor(this, R.color.grey))
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}