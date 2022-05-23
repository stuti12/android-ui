package com.example.evenline_ui.webapi_retrofit.interfaces

import com.example.evenline_ui.utils.Constants
import com.example.evenline_ui.webapi_retrofit.model.LogInRequest
import com.example.evenline_ui.webapi_retrofit.model.LogInResponse
import com.example.evenline_ui.webapi_retrofit.model.SignUpRequest
import com.example.evenline_ui.webapi_retrofit.model.SignUpResponse
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("api/register")
    fun signUpWithRetrofit(@Body signupRequest: SignUpRequest): retrofit2.Call<SignUpResponse>

    @POST("api/login")
    fun  signInWithRetrofit(@Body signinRequest: LogInRequest) : retrofit2.Call<LogInResponse>
    companion object {
        private var interceptor = OkHttpProfilerInterceptor()
        private val okHttpClientBuilder = OkHttpClient.Builder().addInterceptor(interceptor)
            .addInterceptor(
                Interceptor {
                    val builder = it.request().newBuilder()
                    builder.header(Constants.TYPE, Constants.ACCEPT)
                    return@Interceptor it.proceed(builder.build())
                }
            )
        private val okHttpClient = okHttpClientBuilder.build()
        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()

                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASEURL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}