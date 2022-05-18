package com.example.evenline_ui.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.evenline_ui.databinding.ActivityNewPasswordBinding

class NewPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setListener()
    }
    private fun setListener() {
        binding.customToolbar.arrowImageView.setOnClickListener {
            val intent = Intent(this@NewPasswordActivity, ResetPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}