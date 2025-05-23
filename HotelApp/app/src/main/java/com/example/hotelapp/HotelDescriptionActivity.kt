package com.example.hotelapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hotelapp.databinding.ActivityHotelDescriptionBinding

class HotelDescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHotelDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotelDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("HotelAppPrefs", MODE_PRIVATE)
        val username = sharedPref.getString("username", "Guest")
        binding.tvWelcome.text = "Welcome, $username!"

        binding.btnMenu.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }
}
