package com.example.evenline_ui.webapi_retrofit.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.evenline_ui.R
import com.example.evenline_ui.webapi_retrofit.interfaces.ApiCallBackListener
import com.example.evenline_ui.webapi_retrofit.interfaces.ApiInterface
import com.example.evenline_ui.webapi_retrofit.model.ErrorResponse
import com.example.evenline_ui.webapi_retrofit.model.LogInRequest
import com.example.evenline_ui.webapi_retrofit.model.LogInResponse


class LoginViewModel(application: Application) : BaseViewModel(application) {
    var email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val logInResult = MutableLiveData<String>()
    val successResult = MutableLiveData<String>()
    val errorResult = MutableLiveData<String>()
    var isLoggedIn = MutableLiveData<Boolean>()
    private lateinit var userInstance: ApiInterface

    fun performValidation(): Boolean {
        when {
            email.value.isNullOrEmpty() -> {
                logInResult.value =
                    getApplication<Application>().resources.getString(R.string.requreEmailError)
                return false
            }
            password.value.isNullOrEmpty() -> {
                logInResult.value =
                    getApplication<Application>().resources.getString(R.string.passwrdError)
                return false
            }
//            !Patterns.EMAIL_ADDRESS.matcher(email.value).matches() -> {
//                logInResult.value =
//                    getApplication<Application>().resources.getString(R.string.emailError)
//                return false
//            }
            else -> {
                // signInApiCall()
                return true
            }
        }
    }

    fun signInApiCall() {
        userInstance = ApiInterface.create()
        val user = LogInRequest(
            email.value.toString(), password.value.toString()
        )
        val call = userInstance.signInWithRetrofit(user)
        request(call, object : ApiCallBackListener {
            override fun <T> successCallback(data: T?) {
                successResult.postValue(getApplication<Application>().resources.getString(R.string.loginSuccess))
                logInResult.postValue((data as LogInResponse).toString())
                isLoggedIn.postValue(true)
            }

            override fun failureCallback(output: ErrorResponse) {
                errorResult.postValue((output.toString()))
                isLoggedIn.postValue(false)
            }

        })

    }
}