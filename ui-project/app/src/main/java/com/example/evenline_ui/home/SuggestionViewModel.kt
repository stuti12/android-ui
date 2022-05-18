package com.example.evenline_ui.home

import android.provider.Settings.System.getString
import com.example.evenline_ui.R

data class SuggestionViewModel(val image: Int,val title: String ,val textLoc: String,val textPrice: String)

class SuggestionDataClass {
    companion object {
        val suggestionEvent = listOf(SuggestionViewModel(
            R.drawable.image_suggestion_recycel,"Self love stories: A Journaling Workshop","NewYork, NY","Free"),
            SuggestionViewModel(
                R.drawable.image_suggestion_recycel2,"Creative self care: Guide to Intuitive Watercolor","California, CA","$27.99"),
        SuggestionViewModel(
        R.drawable.image_suggestion_recycel3,"Creative self care: Guide to Intuitive Watercolor","NewYork, NY","$25.88")
        )
    }
}