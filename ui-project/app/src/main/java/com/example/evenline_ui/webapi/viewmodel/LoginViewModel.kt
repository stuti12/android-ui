package com.example.evenline_ui.webapi.viewmodel

import android.app.Application
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.example.evenline_ui.R
import com.example.evenline_ui.webapi.ApiInterface
import com.example.evenline_ui.webapi.model.ErrorResponse
import com.example.evenline_ui.webapi.model.SignInResponse
import com.example.evenline_ui.webapi.utils.Constants
import org.json.JSONObject
import java.net.URL

class LoginViewModel(application: Application) : BaseViewModel(application) {

    var email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val logInResult = MutableLiveData<String>()
    val isLoggedIn = MutableLiveData<Boolean>()
    fun performValidation() : Boolean {
        return when {
            email.value.isNullOrEmpty() -> {
                logInResult.value =
                    getApplication<Application>().resources.getString(R.string.requreEmailError)
                false
            }
            password.value.isNullOrEmpty() -> {
                logInResult.value = getApplication<Application>().resources.getString(R.string.passwrdError)
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email.value).matches() -> {
                logInResult.value =
                    getApplication<Application>().resources.getString(R.string.emailError)
                false
            }
            else -> {
                true
            }
        }
    }

    companion object {

    }

    fun performLoginApiCall() {
        val cred = JSONObject()
        cred.apply {
            put("email", email.value)
            put("password", password.value)
        }
        val url = URL(Constants.LOGINURL)
        apiCall(cred, url, Constants.POSTMETHOD, SignInResponse::class.java, object : ApiInterface {
            override fun <T> successCallback(output: String, dataClass: T?) {
                val signUpData = dataClass as SignInResponse
                logInResult.postValue(signUpData.toString())
                isLoggedIn.postValue(true)
                Log.d(getApplication<Application>().resources.getString(R.string.loginSuccess),output)
                //    logInResult.postValue(dataClass.email)
                println(signUpData)
            }

            override fun failureCallback(responseCode: Int, output: ErrorResponse) {
                logInResult.postValue(getApplication<Application>().resources.getString(R.string.loginApiError))
                isLoggedIn.postValue(false)
            }
        })
    }
}
