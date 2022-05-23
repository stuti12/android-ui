package com.example.evenline_ui.webapi_retrofit.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.evenline_ui.webapi_retrofit.interfaces.ApiCallBackListener
import com.example.evenline_ui.webapi_retrofit.model.ErrorResponse
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    fun <T : Any> request(retrofitCall: Call<T>, apiInterface: ApiCallBackListener) {
        retrofitCall.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                response.body()?.let {
                    apiInterface.successCallback(it)
                }

                response.errorBody()?.let {
                    val jsonObj = JSONObject(it.charStream().readText())
                    val errorResponse = jsonObj.getString("error")
                    apiInterface.failureCallback(ErrorResponse(errorResponse))
                }
                if(response.code()== 401) {
                    val jsonObj = JSONObject(response.errorBody()?.charStream()?.readText())
                    val errorResponse = jsonObj.getString("error")
                    apiInterface.failureCallback(ErrorResponse(errorResponse))
                }
                if(response.code() == 404) {
                    val jsonObj = JSONObject(response.errorBody()?.charStream()?.readText())
                    val errorResponse = jsonObj.getString("error")
                    apiInterface.failureCallback(ErrorResponse(errorResponse))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                apiInterface.failureCallback(ErrorResponse(t.toString()))
            }

        })
    }
}