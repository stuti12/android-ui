package com.example.evenline_ui.authentication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.evenline_ui.R
import com.example.evenline_ui.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val bool: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setData()
        }

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            binding.mainSignUpView.hideKeyboard()
        }
        return super.dispatchTouchEvent(ev)
    }
    private fun setSpannableText() {
        binding.textPrivacyPolicy.makeLinks(Pair(
            getString(R.string.privacySpannableText),
            View.OnClickListener {
                Toast.makeText(this@SignUpActivity, "Clicked", Toast.LENGTH_LONG).show()
            }), Pair(getString(R.string.privacySpannableText2), View.OnClickListener {
            Toast.makeText(this@SignUpActivity, "Clicked", Toast.LENGTH_LONG).show()
        }), Pair(getString(R.string.privacySpannableText3), View.OnClickListener {
            Toast.makeText(this@SignUpActivity, "Clicked", Toast.LENGTH_LONG).show()
        })
        )
    }
    private fun setData() {
        setSpannableText()
        binding.apply {
            btnSignUp.setOnClickListener {
                if (validationSuccess()) {
                    Toast.makeText(
                        this@SignUpActivity,
                        getString(R.string.loginSuccess),
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        getString(R.string.loginFail),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            customToolbar.arrowImageView.setOnClickListener {
                val intent = Intent(this@SignUpActivity, GetStartedActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun validationSuccess(): Boolean {
        binding.apply {
            if (etEmail.text.toString().isEmpty()) {
                tflayoutEmail.error = getString(R.string.requreEmailError)
                tflayoutEmail.requestFocus()
                return false
            } else if (!isValidEmail(etEmail.text.toString())) {
                tflayoutEmail.error = getString(R.string.emailError)
                tflayoutEmail.requestFocus()
                return false
            } else if (etPassword.text.toString().trim() == "") {
                tflayoutPassword.error = getString(R.string.passwordRequireError)
                tflayoutPassword.requestFocus()
                return false
            } else if (bool != isValidPassword(etPassword.text.toString())) {
                tflayoutPassword.error = getString(R.string.passwordError)
                tflayoutPassword.requestFocus()
                return false
            } else {
                tflayoutEmail.error = null
                tflayoutPassword.error = null
                return true
            }
            return true
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

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}