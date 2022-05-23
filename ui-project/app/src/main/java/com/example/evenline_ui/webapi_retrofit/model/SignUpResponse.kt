package com.example.evenline_ui.webapi_retrofit.model

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("token")
    val token: String
)

data class ErrorResponse(
    @SerializedName("error")
    val error:String)