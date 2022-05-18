package com.example.evenline_ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.evenline_ui.R
import com.example.evenline_ui.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var sharedPref: SharedPreferences

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNavigationBind()
    }
    private fun bottomNavigationBind() {
        sharedPref = this.getSharedPreferences(
            "sharedpreferences",
            Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean("isLogin",false)
        supportActionBar?.hide()
        val navController= findNavController(R.id.navFragment)
        binding.bottomJetpackNavigation.setupWithNavController(navController)
    }
}