package com.example.evenline_ui.onboarding.data

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import com.example.evenline_ui.R

data class OnBoardDataClass(
    val title: String,
    val subtitle: String,
    val icon: Int
) {
    companion object {
    fun getData(context: Context) : List<OnBoardDataClass> {
          return listOf(OnBoardDataClass(context.getString(R.string.onboardtitle),context.getString(R.string.onboardsubtitle),R.drawable.onboarding),
              OnBoardDataClass(context.getString(R.string.onboardtitle2),context.getString(R.string.onboardsubtitle2),R.drawable.onboarding2)
          )
       }
    }

}