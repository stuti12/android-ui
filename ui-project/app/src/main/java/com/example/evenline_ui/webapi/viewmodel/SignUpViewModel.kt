package com.example.evenline_ui.webapi.viewmodel

import android.app.Application
import android.app.Dialog
import android.util.Log
import android.util.Patterns
import android.view.Window
import androidx.lifecycle.MutableLiveData
import com.example.evenline_ui.R
import com.example.evenline_ui.webapi.ApiInterface
import com.example.evenline_ui.webapi.model.ErrorResponse
import com.example.evenline_ui.webapi.model.SignUpResponse
import com.example.evenline_ui.webapi.utils.Constants
import org.json.JSONObject
import java.net.URL

class SignUpViewModel(application: Application) : BaseViewModel(application) {
    var isRegistered = MutableLiveData<Boolean>()
    var name: MutableLiveData<String> = MutableLiveData()
    var email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val logInResult = MutableLiveData<String>()
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
            !Patterns.EMAIL_ADDRESS.matcher(email.value).matches() -> {
                logInResult.value =
                    getApplication<Application>().resources.getString(R.string.emailError)
                return false
            }
//            password.value.isNullOrEmpty() -> {
//                logInResult.value = getApplication<Application>().resources.getString(R.string.passwrdError)
//                false
//            }
            else -> {
                return true
            }
        }
    }
    companion object {
        lateinit var dialog: Dialog
        private fun showDialog() {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.custom_progressbar)
            dialog.show()
        }
    }
     fun performSignUpApiCall() {
        val cred = JSONObject()
        cred.apply {
            put("name", name.value)
            put("email", email.value)
            put("password", password.value)
        }
        val url = URL(Constants.BASEURL)
        apiCall(cred, url, Constants.POSTMETHOD, SignUpResponse::class.java, object : ApiInterface {
            override fun <T> successCallback(output: String, dataClass: T?) {
                val signUpData  = dataClass as SignUpResponse
                logInResult.postValue(getApplication<Application>().resources.getString(R.string.loginSuccess))
                isRegistered.postValue(true)
                Log.d(getApplication<Application>().resources.getString(R.string.loginSuccess),output)
                //    logInResult.postValue(dataClass.email)
                println(signUpData)
            }

            override fun failureCallback(responseCode: Int, output: ErrorResponse) {
                logInResult.postValue(getApplication<Application>().resources.getString(R.string.loginApiError))
                isRegistered.postValue(false)
            }
        })
    }
}

