package com.example.quizeeapp.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizeeapp.MainActivity
import com.example.quizeeapp.R
import com.example.quizeeapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getAnswers()
        initClick()
    }

    private fun initClick() {
        binding.btnPlayAgain.setOnClickListener {
            startActivity(Intent(this@ResultActivity,MainActivity::class.java))
        }
    }

    private fun getAnswers(){
        val ca = intent.getIntExtra("correctAnswer",0)
        binding.txtCorrectAnswer.text = "$ca"
        binding.txtWrongAnswer.text = "${10-ca}"
    }
}