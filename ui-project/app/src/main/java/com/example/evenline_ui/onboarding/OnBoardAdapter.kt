package com.example.evenline_ui.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.evenline_ui.databinding.ActivityOnBoardingOneBinding
import com.example.evenline_ui.onboarding.data.OnBoardDataClass

class ViewPagerAdapter(var list: List<OnBoardDataClass>) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
    private lateinit var binding: ActivityOnBoardingOneBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ActivityOnBoardingOneBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = list[position]
        // sets the image to the imageview from our itemHolder class
        binding.imageView.setImageResource(ItemsViewModel.icon)

        // sets the text to the textview from our itemHolder class
        binding.titleTextView.text = ItemsViewModel.title
        binding.subtitleTextView.text = ItemsViewModel.subtitle

    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

    }
}