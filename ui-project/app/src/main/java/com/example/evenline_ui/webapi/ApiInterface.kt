package com.example.evenline_ui.webapi

import com.example.evenline_ui.webapi.model.ErrorResponse

interface ApiInterface {
    fun<T> successCallback(output:String , dataClass:T? = null)
    fun failureCallback(responseCode:Int,output:ErrorResponse)
}