package com.example.evenline_ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.evenline_ui.databinding.UpcomingRecycleLayoutBinding
import com.example.evenline_ui.home.DetailActivity
import com.example.evenline_ui.home.ItemsViewModel

class RecycleHomePopularEventAdapter(private val mList: List<ItemsViewModel>) :
    RecyclerView.Adapter<RecycleHomePopularEventAdapter.MyViewHolder>() {
    private lateinit var binding: UpcomingRecycleLayoutBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        binding = UpcomingRecycleLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecycleHomePopularEventAdapter.MyViewHolder,
        position: Int
    ) {
        with(holder) {
            val ItemsViewModel = mList[position]
            binding.imageEvent.setImageResource(ItemsViewModel.image)
            // sets the text to the textview from our itemHolder class
            binding.textDate.text = ItemsViewModel.textDate
            binding.textTime.text = ItemsViewModel.textTime
            binding.textSubtitleRecycleView.text = ItemsViewModel.textTitle
            binding.imageLocation.text = ItemsViewModel.imageLoc
            binding.textPrice.text = ItemsViewModel.textPrice
            binding.imageEvent.setOnClickListener {
                val intent = Intent(holder.itemView.context, DetailActivity::class.java)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class MyViewHolder(var binding: UpcomingRecycleLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}