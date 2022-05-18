package com.example.evenline_ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.evenline_ui.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setData()
    }
    private fun setData(){
        binding.apply {
            customToolbar.arrowImageView.setOnClickListener {
                val intent = Intent(this@DetailActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}