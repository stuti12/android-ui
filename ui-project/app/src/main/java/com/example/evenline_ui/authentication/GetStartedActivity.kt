package com.example.evenline_ui.authentication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.evenline_ui.R
import com.example.evenline_ui.databinding.ActivityGetStartedBinding
import kotlinx.android.synthetic.main.activity_get_started.textGotoSignUp

class GetStartedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGetStartedBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        sharedPreferences = this.getSharedPreferences(
            "sharedPreference", Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("isLogin", false)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setListener()

    }

    private fun setListener() {
        supportActionBar?.hide()

        val spannable = SpannableString(binding.textGotoSignUp.text)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val clickableSpan: ClickableSpan = object : ClickableSpan() {
                override fun onClick(p0: View) {
                    val intent = Intent(this@GetStartedActivity, SignUpActivity::class.java)
                    startActivity(intent)
                }
            }
            spannable.setSpan(clickableSpan,
                23, // start
                30, // end
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )

            binding.apply {
                textGotoSignUp.text = spannable
                textGotoSignUp.movementMethod = LinkMovementMethod.getInstance()
                textGotoSignUp.setOnClickListener {
                    val intent = Intent(this@GetStartedActivity, SignUpActivity::class.java)
                    startActivity(intent)
                }
                btnSignIn.setOnClickListener {
                    val intent = Intent(this@GetStartedActivity, LoginActivity::class.java)
                    startActivity(intent)
                }
                btnGoogle.setOnClickListener {
                    Toast.makeText(this@GetStartedActivity,getString(R.string.btnGoogleText),Toast.LENGTH_SHORT).show()
                }
                btnApple.setOnClickListener {
                    Toast.makeText(this@GetStartedActivity,getString(R.string.btnAppleText),Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}