package com.example.evenline_ui.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.evenline_ui.MainActivity
import com.example.evenline_ui.databinding.ActivityVerificationCodeBinding
import com.example.evenline_ui.home.HomeActivity

class VerificationCodeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVerificationCodeBinding
    private val verificationViewModel: VerificationCodeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setData()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            binding.mainView.hideKeyboard()
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun setData() {
        binding.apply {
            lifecycleOwner = this@VerificationCodeActivity
            viewModel = verificationViewModel
            customToolbar.arrowImageView.setOnClickListener {
                val intent = Intent(this@VerificationCodeActivity, LoginActivity::class.java)
                startActivity(intent)
            }
            btnVerifyPassword.setOnClickListener {
                val intent = Intent(this@VerificationCodeActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
//            etOtp.addTextChangedListener {
//                if (it.toString().trim().isNotEmpty()) {
//                    binding.etOtp2.requestFocus()
//                }
//            }
//            etOtp2.addTextChangedListener {
//                if (it.toString().trim().isNotEmpty()) {
//                    binding.etOtp3.requestFocus()
//                } else {
//                    binding.etOtp.requestFocus()
//                }
//            }
//            etOtp3.addTextChangedListener {
//                if (it.toString().trim().isNotEmpty()) {
//                    binding.etOtp4.requestFocus()
//                } else {
//                    binding.etOtp2.requestFocus()
//                }
//            }
//            etOtp4.addTextChangedListener {
//                if (it.toString().trim().isNotEmpty()) {
//                    val hideKeyboard =
//                        getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
//                    hideKeyboard?.hideSoftInputFromWindow(etOtp4.windowToken, 0)
//                } else {
//                    binding.etOtp3.requestFocus()
//                }
//            }

            verificationViewModel.otp1.observe(this@VerificationCodeActivity) {
                if (it.length == 1) {
                    etOtp2.requestFocus()
                }
                if (it.isNotEmpty()) {
                    etOtp.clearFocus()
                }
            }
            verificationViewModel.otp2.observe(this@VerificationCodeActivity) {
                if (it.length == 1) {
                    etOtp3.requestFocus()
                }
                if (it.isEmpty()) {
                    etOtp.requestFocus()
                }
            }
            verificationViewModel.otp3.observe(this@VerificationCodeActivity) {
                if (it.length == 1) {
                    etOtp4.requestFocus()
                }
                if (it.isEmpty()) {
                    etOtp2.requestFocus()
                }
            }
            verificationViewModel.otp4.observe(this@VerificationCodeActivity) {
                if (it.length == 1) {
                    etOtp2.clearFocus()
                    val hideKeyboard =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    hideKeyboard?.hideSoftInputFromWindow(etOtp4.windowToken, 0)
                }
                if (it.isEmpty()) {
                    etOtp3.requestFocus()
                }
            }
        }
    }
}