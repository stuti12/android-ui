package com.example.evenline_ui.authentication

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.evenline_ui.R
import com.example.evenline_ui.databinding.ActivityLoginBinding
import com.example.evenline_ui.home.HomeActivity
import com.example.evenline_ui.webapi.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    val bool: Boolean = true
    private lateinit var binding: ActivityLoginBinding
    val signinViewModel: LoginViewModel by viewModels()
    lateinit var dialog: Dialog

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = Dialog(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setData()
        observeData()
        supportActionBar?.hide()
    }

    private fun showDialog() {
       // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_progressbar)
        dialog.show()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            binding.mainLoginView.hideKeyboard()
        }
        return super.dispatchTouchEvent(ev)
    }

    fun signInButtonClick(view: View) {
        showDialog()
        if (signinViewModel.performValidation()) {
            signinViewModel.performLoginApiCall()
        }

    }

    private fun observeData() {
        signinViewModel.isLoggedIn.observe(this) { result ->
            if (result == true) {
                dialog.dismiss()
                Toast.makeText(this@LoginActivity, signinViewModel.logInResult.value, Toast.LENGTH_LONG).show()
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                finish()

            } else {
                dialog.dismiss()
                Toast.makeText(this@LoginActivity, signinViewModel.logInResult.value, Toast.LENGTH_LONG).show()
            }
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

    private fun setData() {
        spannableTextSet()
        sharedPreferences = this.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE)
        binding.apply {
            lifecycleOwner = this@LoginActivity
            loginmodel = signinViewModel
            textForgetPassword.setOnClickListener {
                val intent = Intent(this@LoginActivity, ForgetPasswordActivity::class.java)
                startActivity(intent)
            }
            customToolbar.arrowImageView.setOnClickListener {
                val intent = Intent(this@LoginActivity, GetStartedActivity::class.java)
                startActivity(intent)
            }
            btnGoogle.setOnClickListener {
            }

        }
    }

}