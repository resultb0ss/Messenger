package com.example.messenger.ui.main.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.messenger.databinding.ActivityAuthBinding
import com.example.messenger.ui.main.auth.viewpager.ViewPagerAdapter
import com.example.messenger.ui.main.auth.viewpager.ViewPagersPicture

class AuthActivity : AppCompatActivity() {

    private var _binding: ActivityAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}