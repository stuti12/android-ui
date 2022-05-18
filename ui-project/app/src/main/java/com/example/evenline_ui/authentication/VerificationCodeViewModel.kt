package com.example.evenline_ui.authentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VerificationCodeViewModel: ViewModel() {
        var otp1: MutableLiveData<String> = MutableLiveData()
        var otp2: MutableLiveData<String> = MutableLiveData()
        var otp3: MutableLiveData<String> = MutableLiveData()
        var otp4: MutableLiveData<String> = MutableLiveData()
}