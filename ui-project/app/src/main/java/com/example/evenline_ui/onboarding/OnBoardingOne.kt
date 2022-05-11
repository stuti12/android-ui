package com.example.evenline_ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.evenline_ui.MainActivity
import com.example.evenline_ui.R
import com.example.evenline_ui.databinding.ActivityOnBoardingBinding
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

    }
}