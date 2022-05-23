package com.example.evenline_ui.webapi_retrofit.interfaces

import com.example.evenline_ui.webapi_retrofit.model.ErrorResponse

interface ApiCallBackListener {
    fun<T> successCallback(data: T?)
    fun failureCallback(output: ErrorResponse)
}