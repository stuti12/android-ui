package com.example.evenline_ui.authentication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.evenline_ui.R
import com.example.evenline_ui.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_sign_up.tflayoutUserName

class LoginActivity : AppCompatActivity() {
    val bool: Boolean = true
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()

        supportActionBar?.hide()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            binding.mainLoginView.hideKeyboard()
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun setSpannableText() {
        binding.textPrivacyPolicy.makeLinks(Pair(
            getString(R.string.privacySpannableText),
            View.OnClickListener {
                Toast.makeText(this@LoginActivity, "Clicked", Toast.LENGTH_LONG).show()
            }), Pair(getString(R.string.privacySpannableText2), View.OnClickListener {
            Toast.makeText(this@LoginActivity, "Clicked", Toast.LENGTH_LONG).show()
        }), Pair(getString(R.string.privacySpannableText3), View.OnClickListener {
            Toast.makeText(this@LoginActivity, "Clicked", Toast.LENGTH_LONG).show()
        })
        )
    }

    private fun setData() {
        setSpannableText()
        sharedPreferences = this.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE)
        binding.apply {

            btnSignIn.setOnClickListener {
                if (validationSuccess()) {
                    Toast.makeText(
                        this@LoginActivity,
                        getString(R.string.loginSuccess),
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this@LoginActivity, VerificationCodeActivity::class.java)
                    startActivity(intent)
                    finish()
//                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
//                    editor.putBoolean("isLogin", true)
//                    editor.apply()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        getString(R.string.loginFail),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            textForgetPassword.setOnClickListener {
                val intent = Intent(this@LoginActivity, ForgetPasswordActivity::class.java)
                startActivity(intent)
            }
            customToolbar.arrowImageView.setOnClickListener {
                val intent = Intent(this@LoginActivity, GetStartedActivity::class.java)
                startActivity(intent)
            }

            etEmail.doOnTextChanged { text, _, _, _ ->
                when {
                    text.toString().isEmpty() -> tflayoutEmail.error =
                        getString(R.string.requreEmailError)
                    !Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString())
                        .matches() -> tflayoutEmail.error = getString(R.string.emailError)
                    else -> {
                        tflayoutEmail.error = null
                    }
                }
            }
            etPassword.doOnTextChanged { text, _, _, _ ->
                when {
                    text.toString().isEmpty() -> tflayoutPassword.error =
                        getString(R.string.passwordRequireError)
                    else -> {
                        tflayoutPassword.error = null
                        tflayoutPassword.isEndIconVisible
                    }
                }
            }

        }
    }

    private fun validationSuccess(): Boolean {
        binding.apply {

            when {
                etEmail.text.toString().isEmpty() -> {
                    tflayoutEmail.error = getString(R.string.requireUserNameError)
                    tflayoutEmail.requestFocus()
                    return false
                }
                !isValidEmail(etEmail.text.toString()) -> {
                    tflayoutEmail.error = getString(R.string.emailError)
                    tflayoutEmail.requestFocus()
                    return false
                }
                etPassword.text.toString().trim() == "" -> {
                    tflayoutPassword.error = getString(R.string.passwordRequireError)
                    tflayoutPassword.requestFocus()
                    return false
                }
                bool != isValidPassword(etPassword.text.toString()) -> {
                    tflayoutPassword.error = getString(R.string.passwordError)
                    tflayoutPassword.requestFocus()
                    return false
                }
                else -> {
                    tflayoutEmail.error = null
                    tflayoutPassword.error = null
                    return true
                }
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