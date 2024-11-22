package com.example.quizeeapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizeeapp.R
import com.example.quizeeapp.data.helper.AuthenticationHelper.Companion.authHelper
import com.example.quizeeapp.databinding.ActivitySignUpBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initClick()
    }
    @OptIn(DelicateCoroutinesApi::class)
    private fun initClick() {
        binding.submitSignUp.setOnClickListener {
            val signUpEmail = binding.edtSignUpEmail.text.toString()
            val signUpPassword = binding.edtSignUpPassword.text.toString()
            if(signUpEmail.isEmpty()){
                binding.tlSingUpEmail.error="Please Enter Email"
            } else if(signUpPassword.isEmpty()){
                binding.tiSignUpPassword.error="Please Enter Password"
            } else{
                GlobalScope.launch {
                    val signUpResult = authHelper.signUp(signUpEmail, signUpPassword)
                    withContext(Dispatchers.Main) {
                        if (signUpResult == "success") {
                            authHelper.checkUser()
                            Toast.makeText(this@SignUpActivity, "Email Registered Successfully",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                        } else {
                            Toast.makeText(this@SignUpActivity,signUpResult, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
        binding.txtAlAcc.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}