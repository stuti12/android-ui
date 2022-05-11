package com.example.evenline_ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.evenline_ui.MainActivity
import com.example.evenline_ui.R
import com.example.evenline_ui.databinding.ActivityOnBoardingBinding
import com.example.evenline_ui.onboarding.data.OnBoardDataClass
import kotlinx.android.synthetic.main.activity_on_boarding.viewpager

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    var modelList: ArrayList<OnBoardDataClass> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        supportActionBar?.hide()

        setContentView(binding.root)
        setUpViewPager()
    }

    private fun setUpViewPager() {
            modelList = ArrayList()
            modelList.apply {
                add(OnBoardDataClass(getString(R.string.onboardtitle),getString(R.string.onboardsubtitle),R.drawable.onboarding))
                add(OnBoardDataClass( getString(R.string.onboardtitle2),getString(R.string.onboardsubtitle2),R.drawable.onboarding2,))
            }
            val adapter = ViewPagerAdapter(modelList)
            binding.viewpager.apply {
                this.adapter = adapter
            }
            binding.btnStarted.setOnClickListener {
                val intent = Intent(this@OnBoardingActivity, MainActivity::class.java)
                startActivity(intent)
            }
    }
}