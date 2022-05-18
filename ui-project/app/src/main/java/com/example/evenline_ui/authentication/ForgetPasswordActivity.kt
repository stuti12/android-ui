package com.example.evenline_ui.authentication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.evenline_ui.R
import com.example.evenline_ui.databinding.ActivityForgetPasswordBinding

class ForgetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgetPasswordBinding
    private var valid: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setData()

    }
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            binding.mainForgetPasswordView.hideKeyboard()
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun setData() {
        binding.apply {
            btnForgotPassword.setOnClickListener {
                if (etEmail.text.toString().trim() == "") {
                    tflayoutEmail.error = getString(R.string.requreEmailError)
                    etEmail.requestFocus()
                    valid = false
                } else if (!isValidEmail(binding.etEmail.text.toString())) {
                    etEmail.error = getString(R.string.emailError)
                    etEmail.requestFocus()
                    valid = false
                } else {
                    tflayoutEmail.error = null
                    Toast.makeText(
                        this@ForgetPasswordActivity,
                        getString(R.string.forgetPasswordMessage),
                        Toast.LENGTH_LONG
                    ).show()
                    val intent =
                        Intent(this@ForgetPasswordActivity, ResetPasswordActivity::class.java)
                    startActivity(intent)
                }
            }
            customToolbar.arrowImageView.setOnClickListener {
                val intent = Intent(this@ForgetPasswordActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //before text change
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!isValidEmail(binding.etEmail.text.toString())) {
                    binding.tflayoutEmail.error = getString(R.string.emailError)
                    binding.etEmail.requestFocus()
                    valid = false
                } else if (binding.etEmail.text.toString().trim() == "") {
                    binding.tflayoutEmail.error = getString(R.string.requreEmailError)
                    binding.etEmail.requestFocus()
                    valid = false
                } else {
                    binding.tflayoutEmail.error = null
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                //after textchange method
            }
        })

    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}