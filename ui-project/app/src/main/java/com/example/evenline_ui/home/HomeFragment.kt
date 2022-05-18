package com.example.evenline_ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evenline_ui.databinding.FragmentHomeBinding
import com.example.evenline_ui.home.adapter.RecycleHomePopularEventAdapter
import com.example.evenline_ui.home.adapter.RecycleHomeSuggestionAdapter
import kotlinx.android.synthetic.main.fragment_home.cardViewPopularEvent

class HomeFragment : Fragment() {
    private val data = ArrayList<ItemsViewModel>()
    private var filterList: ArrayList<ItemsViewModel> = data
    val dataSuggestion = ArrayList<SuggestionViewModel>()
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() {
        cardViewPopularEvent.setOnClickListener {
            val intent = Intent(this@HomeFragment.context, DetailActivity::class.java)
            context?.startActivity(intent)
        }
        binding.apply {
            recycleViewPopular.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            val adapter = RecycleHomePopularEventAdapter(DataClass.popularEvent)
            recycleViewPopular.adapter = adapter


            recycleViewSuggestion.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            val adapter2 = RecycleHomeSuggestionAdapter(SuggestionDataClass.suggestionEvent)
            recycleViewSuggestion.adapter = adapter2
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    performSearch(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    fun performSearch(sText: String) {
        val newList = data.filter {
            it.textTitle.contains(sText, true)
        } as ArrayList<ItemsViewModel>

        filterList = newList
        //notifyDataSetChanged()
    }

}