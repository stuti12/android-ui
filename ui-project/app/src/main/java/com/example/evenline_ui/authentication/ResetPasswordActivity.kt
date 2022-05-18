package com.example.evenline_ui.authentication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.evenline_ui.R
import com.example.evenline_ui.databinding.ActivityResetPasswordBinding

class ResetPasswordActivity : AppCompatActivity() {
    lateinit var binding: ActivityResetPasswordBinding
    private var valid: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        setData()
    }
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            binding.mainResetPasswordScrollView.hideKeyboard()
        }
        return super.dispatchTouchEvent(ev)
    }
    private fun setData() {
        binding.apply {
            btnResetPassword.setOnClickListener {
                when {
                    etPassword.text.toString().trim() == "" -> {
                        etPassword.error = getString(R.string.requreEmailError)
                        etPassword.requestFocus()
                        valid = false
                    }
                    etConfirmPassword.text.toString().trim() == "" -> {
                        etConfirmPassword.error = getString(R.string.requreEmailError)
                        etConfirmPassword.requestFocus()
                        valid = false
                    }
                    etConfirmPassword.text.toString() != binding.etPassword.text.toString() -> {
                        etConfirmPassword.error = getString(R.string.passwordNotMatch)
                        valid = false
                    }
                    else -> {
                        Toast.makeText(
                            this@ResetPasswordActivity,
                            getString(R.string.resetPasswordText),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        val intent =
                            Intent(this@ResetPasswordActivity, NewPasswordActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
            customToolbar.arrowImageView.setOnClickListener {
                val intent = Intent(this@ResetPasswordActivity, ForgetPasswordActivity::class.java)
                startActivity(intent)
            }

        }
    }

    private fun isValidPassword(password: String?): Boolean {
        password?.let {
            val passwordPattern =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
            val passwordMatcher = Regex(passwordPattern)
            return passwordMatcher.find(password) != null
        } ?: return false
    }
}