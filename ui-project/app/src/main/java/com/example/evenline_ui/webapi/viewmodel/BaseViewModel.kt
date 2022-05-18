package com.example.evenline_ui.webapi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.evenline_ui.webapi.ApiInterface
import com.example.evenline_ui.webapi.model.ErrorResponse
import com.example.evenline_ui.webapi.utils.Constants
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

open class BaseViewModel(application: Application): AndroidViewModel(application){

    fun<T> apiCall(jsonObject: JSONObject, url: URL, requestmethod: String, data: Class<T>,
                   httpCallback: ApiInterface
    ){
        viewModelScope.launch(Dispatchers.IO) {
            val httpURLConnection = url.openConnection() as HttpURLConnection
            with(httpURLConnection) {
                requestMethod = requestmethod
                setRequestProperty("Content-Type", Constants.TYPE)
                val writer = OutputStreamWriter(outputStream)
                writer.write(jsonObject.toString())
                writer.flush()
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val response = httpURLConnection.inputStream.bufferedReader().use { it.readText() }
                    println(response)
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val dataObject = gson.fromJson(response,data)
                    httpCallback.successCallback(responseMessage,dataObject)
                } else {
                    val response = httpURLConnection.errorStream.bufferedReader().use { it.readText() }
                    println(response)
                    httpCallback.failureCallback(responseCode, ErrorResponse((responseCode.toString())))
                }
            }
        }
    }
}