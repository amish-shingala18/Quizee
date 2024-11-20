package com.example.quizeeapp.view.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizeeapp.R
import com.example.quizeeapp.databinding.ActivityQuizBinding
import com.example.quizeeapp.viewmodel.QuizViewModel

@Suppress("DEPRECATION")
class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var dialog: Dialog
    private var countDownTimer: CountDownTimer?=null
    private val quizViewModel by viewModels<QuizViewModel>()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val getCategory = intent.getStringExtra("category")
        quizViewModel.category = getCategory
        quizViewModel.getData()
        binding.btnNext.visibility=View.GONE
        initClick()
        selectAtleastOne()
        startTimer()
        showLoader()
        quizViewModel.quizLiveData.observe(this){
            binding.txtQuestions.text=it[0].question
            binding.txtOption1.text=it[0].allOptions[0]
            binding.txtOption2.text=it[0].allOptions[1]
            binding.txtOption3.text=it[0].allOptions[2]
            binding.txtOption4.text=it[0].allOptions[3]
            dialog.dismiss()
            countDownTimer?.start()
        }
        quizViewModel.index.observe(this){
            binding.cvOption1.isClickable=true
            binding.cvOption2.isClickable=true
            binding.cvOption3.isClickable=true
            binding.cvOption4.isClickable=true
            binding.txtQuestions.text = quizViewModel.quizLiveData.value?.get(it)?.question
            binding.txtOption1.text = quizViewModel.quizLiveData.value?.get(it)?.allOptions?.get(0)
            binding.txtOption2.text = quizViewModel.quizLiveData.value?.get(it)?.allOptions?.get(1)
            binding.txtOption3.text = quizViewModel.quizLiveData.value?.get(it)?.allOptions?.get(2)
            binding.txtOption4.text = quizViewModel.quizLiveData.value?.get(it)?.allOptions?.get(3)
            binding.cvOption1.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.cvOption2.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.cvOption3.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.cvOption4.setCardBackgroundColor(resources.getColor(R.color.white))
            binding.btnNext.visibility = View.GONE
            if (quizViewModel.index.value!!+1 == 10) {
                binding.btnNext.text = "SUBMIT"
                binding.btnNext.setOnClickListener {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("correctAnswer",quizViewModel.correctAnswer)
                    startActivity(intent)
                }
            } else {
                binding.btnNext.text = "Next"
            }
        }
    }
    @SuppressLint("SetTextI18n")
    private fun initClick(){
        binding.btnNext.setOnClickListener {
            quizViewModel.changeQuestion()
            binding.txtCount.text = "${quizViewModel.index.value!!+1}"
            countDownTimer?.cancel()
            countDownTimer?.start()
        }
    }
    private fun showLoader() {
        dialog = Dialog(this)
        dialog.setContentView(R.layout.question_loader)
        dialog.show()
    }
    private fun selectAtleastOne(){
        binding.cvOption1.setOnClickListener {
            if(binding.txtOption1.text==quizViewModel.quizLiveData.value!![0].correctAnswer){
                binding.cvOption1.setCardBackgroundColor(resources.getColor(R.color.green))
                binding.cvOption1.isClickable=false
                binding.cvOption2.isClickable=false
                binding.cvOption3.isClickable=false
                binding.cvOption4.isClickable=false
            }else{
                binding.cvOption1.setCardBackgroundColor(resources.getColor(R.color.red))
                if(binding.txtOption2.text==quizViewModel.quizLiveData.value!![0].correctAnswer){
                    binding.cvOption2.setCardBackgroundColor(resources.getColor(R.color.green))
                }else if(binding.txtOption3.text==quizViewModel.quizLiveData.value!![0].correctAnswer){
                    binding.cvOption3.setCardBackgroundColor(resources.getColor(R.color.green))
                }else if(binding.txtOption4.text==quizViewModel.quizLiveData.value!![0].correctAnswer){
                    binding.cvOption4.setCardBackgroundColor(resources.getColor(R.color.green))
                }
                binding.cvOption1.isClickable=false
                binding.cvOption2.isClickable=false
                binding.cvOption3.isClickable=false
                binding.cvOption4.isClickable=false
            }
            binding.btnNext.visibility=View.VISIBLE
        }
        binding.cvOption2.setOnClickListener {
            if(binding.txtOption2.text==quizViewModel.quizLiveData.value!![0].correctAnswer){
                binding.cvOption2.setCardBackgroundColor(resources.getColor(R.color.green))
                binding.cvOption1.isClickable=false
                binding.cvOption2.isClickable=false
                binding.cvOption3.isClickable=false
                binding.cvOption4.isClickable=false
            }else{
                binding.cvOption2.setCardBackgroundColor(resources.getColor(R.color.red))
                if(binding.txtOption1.text==quizViewModel.quizLiveData.value!![0].correctAnswer){
                    binding.cvOption1.setCardBackgroundColor(resources.getColor(R.color.green))
                }else if(binding.txtOption3.text==quizViewModel.quizLiveData.value!![0].correctAnswer){
                    binding.cvOption3.setCardBackgroundColor(resources.getColor(R.color.green))
                }else if(binding.txtOption4.text==quizViewModel.quizLiveData.value!![0].correctAnswer){
                    binding.cvOption4.setCardBackgroundColor(resources.getColor(R.color.green))
                }
                binding.cvOption1.isClickable=false
                binding.cvOption2.isClickable=false
                binding.cvOption3.isClickable=false
                binding.cvOption4.isClickable=false
            }
            binding.btnNext.visibility=View.VISIBLE
        }
        binding.cvOption3.setOnClickListener {
            if(binding.txtOption3.text==quizViewModel.quizLiveData.value!![0].correctAnswer){
                binding.cvOption3.setCardBackgroundColor(resources.getColor(R.color.green))
                binding.cvOption1.isClickable=false
                binding.cvOption2.isClickable=false
                binding.cvOption3.isClickable=false
                binding.cvOption4.isClickable=false
            }else{
                binding.cvOption3.setCardBackgroundColor(resources.getColor(R.color.red))
                if(binding.txtOption2.text==quizViewModel.quizLiveData.value!![0].correctAnswer){
                    binding.cvOption2.setCardBackgroundColor(resources.getColor(R.color.green))
                }else if(binding.txtOption1.text==quizViewModel.quizLiveData.value!![0].correctAnswer){
                    binding.cvOption1.setCardBackgroundColor(resources.getColor(R.color.green))
                }else if(binding.txtOption4.text==quizViewModel.quizLiveData.value!![0].correctAnswer){
                    binding.cvOption4.setCardBackgroundColor(resources.getColor(R.color.green))
                }
                binding.cvOption1.isClickable=false
                binding.cvOption2.isClickable=false
                binding.cvOption3.isClickable=false
                binding.cvOption4.isClickable=false
            }
            binding.btnNext.visibility=View.VISIBLE
        }
        binding.cvOption4.setOnClickListener {
            if(binding.txtOption4.text==quizViewModel.quizLiveData.value!![0].correctAnswer){
                binding.cvOption4.setCardBackgroundColor(resources.getColor(R.color.green))
                binding.cvOption1.isClickable=false
                binding.cvOption2.isClickable=false
                binding.cvOption3.isClickable=false
                binding.cvOption4.isClickable=false
            }else{
                binding.cvOption4.setCardBackgroundColor(resources.getColor(R.color.red))
                if(binding.txtOption2.text==quizViewModel.quizLiveData.value!![0].correctAnswer){
                    binding.cvOption2.setCardBackgroundColor(resources.getColor(R.color.green))
                }else if(binding.txtOption3.text==quizViewModel.quizLiveData.value!![0].correctAnswer){
                    binding.cvOption3.setCardBackgroundColor(resources.getColor(R.color.green))
                }else if(binding.txtOption1.text==quizViewModel.quizLiveData.value!![0].correctAnswer){
                    binding.cvOption1.setCardBackgroundColor(resources.getColor(R.color.green))
                }
                binding.cvOption1.isClickable=false
                binding.cvOption2.isClickable=false
                binding.cvOption3.isClickable=false
                binding.cvOption4.isClickable=false
            }
            binding.btnNext.visibility=View.VISIBLE
        }
    }
    private fun startTimer() {
        countDownTimer = object : CountDownTimer(30000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                binding.txtSeconds.text = (millisUntilFinished / 1000).toString()
            }
            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                quizViewModel.optionSelected=null
                quizViewModel.changeQuestion()
                countDownTimer?.cancel()
                countDownTimer?.start()
                binding.txtCount.text = "${quizViewModel.index.value!!+1}"
            }
        }
    }
}