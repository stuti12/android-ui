package com.example.evenline_ui.authentication

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.evenline_ui.R
import com.example.evenline_ui.databinding.ActivitySignUpBinding
import com.example.evenline_ui.webapi_retrofit.interfaces.ApiInterface
import com.example.evenline_ui.webapi_retrofit.viewmodel.SignUpViewModel


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val bool: Boolean = true
    val signupViewModel: SignUpViewModel by viewModels()
    lateinit var dialog: Dialog

    private lateinit var userInstance: ApiInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        super.onCreate(savedInstanceState)
        dialog = Dialog(this)
        supportActionBar?.hide()
        setData()
        observeData()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            binding.mainSignUpView.hideKeyboard()
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun spannableTextSet() {
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

    private fun observeData() {
        signupViewModel.isRegistered.observe(this) { result ->
            if (result == true) {
                dialog.dismiss()
                Toast.makeText(
                    this@SignUpActivity,
                    signupViewModel.logInResult.value,
                    Toast.LENGTH_LONG
                ).show()
                startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                finish()
            } else {
                dialog.dismiss()
                Toast.makeText(
                    this@SignUpActivity,
                    signupViewModel.logInResult.value,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        signupViewModel.email.observe(this) {
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

    fun signUpButtonClick(view: View) {
        if (signupViewModel.performValidation()) {
            showDialog()
            signupViewModel.signUpApiCall()

        } else {
            Toast.makeText(
                this@SignUpActivity,
                getString(R.string.loginFail),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun setData() {

        binding.apply {
            spannableTextSet()
            lifecycleOwner = this@SignUpActivity
            viewmodel = signupViewModel

            customToolbar.arrowImageView.setOnClickListener {
                val intent = Intent(this@SignUpActivity, GetStartedActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun showDialog() {
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_progressbar)
        dialog.show()
    }
}