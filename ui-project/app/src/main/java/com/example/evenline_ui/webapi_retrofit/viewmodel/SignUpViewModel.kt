package com.example.evenline_ui.webapi_retrofit.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.evenline_ui.R
import com.example.evenline_ui.webapi_retrofit.interfaces.ApiCallBackListener
import com.example.evenline_ui.webapi_retrofit.interfaces.ApiInterface
import com.example.evenline_ui.webapi_retrofit.model.ErrorResponse
import com.example.evenline_ui.webapi_retrofit.model.SignUpRequest
import com.example.evenline_ui.webapi_retrofit.model.SignUpResponse

class SignUpViewModel(application: Application) : BaseViewModel(application) {
    var name: MutableLiveData<String> = MutableLiveData()
    var email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    private lateinit var userInstance: ApiInterface
    val logInResult = MutableLiveData<String>()
    val successResult = MutableLiveData<String>()
    val errorResult = MutableLiveData<String>()
    val isRegistered = MutableLiveData<Boolean>()

    fun performValidation(): Boolean {
        when {
            name.value.isNullOrEmpty() -> {
                logInResult.value =
                    getApplication<Application>().resources.getString(R.string.requireUserNameError)
                return false
            }
            email.value.isNullOrEmpty() -> {
                logInResult.value =
                    getApplication<Application>().resources.getString(R.string.requreEmailError)
                return false
            }
//            !Patterns.EMAIL_ADDRESS.matcher(email.value).matches() -> {
//                logInResult.value =
//                    getApplication<Application>().resources.getString(R.string.emailError)
//                return false
//            }
            password.value.isNullOrEmpty() -> {
                logInResult.value = getApplication<Application>().resources.getString(R.string.passwrdError)
                return false
            }
            else -> {
               // signUpApiCall()
                 return true
            }
        }
    }

    fun signUpApiCall() {
        userInstance = ApiInterface.create()
        val user = SignUpRequest(
            email.value.toString(), password.value.toString()
        )
        val call = userInstance.signUpWithRetrofit(user)
        request(call,object : ApiCallBackListener {
            override fun <T> successCallback(data: T?) {
                logInResult.postValue((data as SignUpResponse).toString())
                successResult.postValue(getApplication<Application>().resources.getString(R.string.loginSuccess))
                isRegistered.postValue(true)
            }

            override fun failureCallback(output: ErrorResponse) {
                logInResult.postValue((output.toString()))
                isRegistered.postValue(false)
            }

        })
    }

}