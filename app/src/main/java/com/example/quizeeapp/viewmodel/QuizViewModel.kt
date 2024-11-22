package com.example.quizeeapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizeeapp.data.helper.ApiHelper.Companion.apiHelper
import com.example.quizeeapp.data.model.ShuffleModel
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {
    private var quizMutableLiveData = MutableLiveData<MutableList<ShuffleModel>>()
    val quizLiveData: LiveData<MutableList<ShuffleModel>> = quizMutableLiveData
    private val dummyList = mutableListOf<ShuffleModel>()
    var category:String?=null
    var correctAnswer:Int=0
    var optionSelected:String?=null
    private var mutableIndex = MutableLiveData(0)
    var index: LiveData<Int> = mutableIndex
    fun getData() {
        viewModelScope.launch {
            val dataList = apiHelper.callApi(category = category!!)
            for(i in dataList!!.results!!){
                val options = i!!.incorrectAnswers!!.toMutableList()
                options.add(i.correctAnswer)
                options.shuffle()
                dummyList.add(ShuffleModel(i.question!!,i.correctAnswer!!,options))
                quizMutableLiveData.value = dummyList
            }
        }
    }
    fun changeQuestion() {
        if (mutableIndex.value!! < 9) {
            checkAnswers()
            Log.d("TAG", "changeQuestion: ++++++++++++++++++++++++++++   $optionSelected===========${quizLiveData.value!![index.value!!].correctAnswer}")
            mutableIndex.value = mutableIndex.value!! + 1
        }
    }
    fun checkAnswers(){
        if(optionSelected==quizLiveData.value?.get(index.value!!)?.correctAnswer){
            correctAnswer += 1
            Log.d("TAG", "checkAnswers: =============$correctAnswer")
        }
    }
}