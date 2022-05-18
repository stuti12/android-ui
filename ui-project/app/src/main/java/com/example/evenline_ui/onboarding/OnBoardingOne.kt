package com.example.evenline_ui.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.evenline_ui.databinding.ActivityOnBoardingOneBinding

class OnBoardingOne : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingOneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingOneBinding.inflate(layoutInflater)
//        supportActionBar?.hide()
//        actionBar?.hide()
        setContentView(binding.root)
        setData()
    }
    private fun setData() {
        supportActionBar?.hide()
    }
}