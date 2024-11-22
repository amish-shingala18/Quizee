package com.example.quizeeapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizeeapp.MainActivity
import com.example.quizeeapp.R
import com.example.quizeeapp.data.helper.AuthenticationHelper.Companion.authHelper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splpash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        authHelper.checkUser()
        Handler(Looper.getMainLooper()).postDelayed({
            if (authHelper.user!=null) {
                startActivity(
                    Intent(
                        this@SplashActivity, MainActivity::class.java
                    )
                )
                finish()
            } else {
                startActivity(
                    Intent(
                        this@SplashActivity, SignUpActivity::class.java
                    )
                )
                finish()
            }
        },3000)
    }
}