package com.example.quizeeapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizeeapp.MainActivity
import com.example.quizeeapp.R
import com.example.quizeeapp.data.helper.AuthenticationHelper.Companion.authHelper
import com.example.quizeeapp.databinding.ActivitySignInBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
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
        binding.submitSignIn.setOnClickListener {
            val signUpEmail = binding.edtSignInEmail.text.toString()
            val signUpPassword = binding.edtSignInPassword.text.toString()
            if(signUpEmail.isEmpty()){
                binding.tlSingInEmail.error="Please Enter Email"
            } else if(signUpPassword.isEmpty()){
                binding.tiSignInPassword.error="Please Enter Password"
            } else{
                GlobalScope.launch {
                    val signInResult = authHelper.signIn(signUpEmail, signUpPassword)
                    withContext(Dispatchers.Main) {
                        if (signInResult == "Success") {
                            Toast.makeText(
                                this@SignInActivity,
                                "Signed In Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            authHelper.checkUser()
                            val intent = Intent(this@SignInActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@SignInActivity, signInResult, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
        binding.txtNoAcc.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}