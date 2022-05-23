package com.example.evenline_ui.authentication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.evenline_ui.R
import com.example.evenline_ui.databinding.ActivityLoginBinding
import com.example.evenline_ui.webapi_retrofit.interfaces.ApiInterface
import com.example.evenline_ui.webapi_retrofit.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    val bool: Boolean = true
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    val signinViewModel: LoginViewModel by viewModels()

    private lateinit var userInstance: ApiInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
        observeData()
        supportActionBar?.hide()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            binding.mainLoginView.hideKeyboard()
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun observeData() {
        signinViewModel.logInResult.observe(this) { result ->
            // dialog.dismiss()
            Toast.makeText(this@LoginActivity, result, Toast.LENGTH_LONG).show()

        }
        signinViewModel.email.observe(this) {
            when {
                it.toString().isEmpty() -> binding.tflayoutEmail.error =
                    getString(R.string.requreEmailError)
                !Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString())
                    .matches() -> binding.tflayoutEmail.error = getString(R.string.emailError)
                else -> {
                    binding.tflayoutEmail.error = null
                    binding.tflayoutEmail.setEndIconDrawable(R.drawable.ic_check)
                }
            }
        }
        signinViewModel.isLoggedIn.observe(this) { result ->
            if (result == true) {
                // dialog.dismiss()
                Toast.makeText(
                    this@LoginActivity,
                    signinViewModel.logInResult.value,
                    Toast.LENGTH_LONG
                ).show()
                startActivity(Intent(this@LoginActivity, VerificationCodeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this@LoginActivity, getString(R.string.loginFail), Toast.LENGTH_LONG)
                    .show()

            }
        }
    }

    private fun spannableTextSet() {
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

    fun signInButtonClick(view: View) {
        if (signinViewModel.performValidation()) {
            signinViewModel.signInApiCall()
//            val intent = Intent(this@LoginActivity, VerificationCodeActivity::class.java)
//            startActivity(intent)
//            finish()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean("isLogin", true)
            editor.apply()
        } else {
            Toast.makeText(
                this@LoginActivity,
                getString(R.string.loginFail),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun setData() {
        spannableTextSet()
        sharedPreferences = this.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE)
        binding.apply {
            lifecycleOwner = this@LoginActivity
            viewmodel = signinViewModel
            textForgetPassword.setOnClickListener {
                val intent = Intent(this@LoginActivity, ForgetPasswordActivity::class.java)
                startActivity(intent)
            }
            customToolbar.arrowImageView.setOnClickListener {
                val intent = Intent(this@LoginActivity, GetStartedActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}