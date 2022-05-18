package com.example.evenline_ui.home

import com.example.evenline_ui.R

data class ItemsViewModel(val image: Int, val textDate: String,val textTime: String,val textTitle: String,val imageLoc: String,val textPrice: String)

class DataClass {
    companion object {
        val popularEvent = listOf(ItemsViewModel(R.drawable.upcoming_event_home_recycleimage,"24 Mar 2022" ,"10:00 pm","Battles of Dance Party 20s","California, CA","$27.99"),ItemsViewModel(R.drawable.upcoming_event_home_recycleimage,"24 Mar 2022" ,"10:00 pm","Battles of Dance Party 20s","California, CA","$27.99"),
            ItemsViewModel(R.drawable.upcoming_event_home_recycleimage,"24 May 2022" ,"10:00 pm","California Art Festival","NewYork, NA","$27.99"),
        )
    }
}