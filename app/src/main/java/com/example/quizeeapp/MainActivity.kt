package com.example.quizeeapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizeeapp.databinding.ActivityMainBinding
import com.example.quizeeapp.view.activity.QuizActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initClick()
    }
    private fun initClick() {
        binding.lnrAnimal.setOnClickListener {
            val intent = Intent(this@MainActivity, QuizActivity::class.java)
            intent.putExtra("category", "27")
            startActivity(intent)
        }
        binding.lnrSports.setOnClickListener {
            val intent = Intent(this@MainActivity, QuizActivity::class.java)
            intent.putExtra("category", "21")
            startActivity(intent)
        }
    }
}