package com.example.evenline_ui.webapi_retrofit.model

import com.google.gson.annotations.SerializedName

data class LogInResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("token")
    val token: String
)
