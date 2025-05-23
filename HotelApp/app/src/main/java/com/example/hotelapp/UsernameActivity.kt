package com.example.hotelapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hotelapp.databinding.ActivityUsernameBinding

class UsernameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUsernameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsernameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSaveUsername.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val sharedPref = getSharedPreferences("HotelAppPrefs", Context.MODE_PRIVATE)
            sharedPref.edit().putString("username", username).apply()
            startActivity(Intent(this, HotelDescriptionActivity::class.java))
        }
    }
}
