package com.example.evenline_ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.evenline_ui.R
import com.example.evenline_ui.databinding.ActivityHomeBinding
import com.example.evenline_ui.databinding.SuggestionRecycleLayoutBinding
import com.example.evenline_ui.home.ItemsViewModel
import com.example.evenline_ui.home.SuggestionViewModel

class RecycleHomeSuggestionAdapter(private val mList: List<SuggestionViewModel>) :
    RecyclerView.Adapter<RecycleHomeSuggestionAdapter.MyViewHolder>() {
    private lateinit var binding: SuggestionRecycleLayoutBinding
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MyViewHolder {
        binding = SuggestionRecycleLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class MyViewHolder(var binding: SuggestionRecycleLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val SuggestionViewModel = mList[position]
        with(holder) {
            // sets the image to the imageview from our itemHolder class
            binding.imageEvent.setImageResource(SuggestionViewModel.image)
            binding.textTitle.text = SuggestionViewModel.title
            // sets the text to the textview from our itemHolder class
            binding.imageLocation.text = SuggestionViewModel.textLoc
            binding.textPrice.text = SuggestionViewModel.textPrice
        }
    }
}