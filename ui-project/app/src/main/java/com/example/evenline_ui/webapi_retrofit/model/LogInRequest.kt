package com.example.evenline_ui.webapi_retrofit.model

import com.google.gson.annotations.SerializedName

data class LogInRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String?
)