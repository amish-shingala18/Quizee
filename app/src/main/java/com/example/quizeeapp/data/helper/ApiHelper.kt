package com.example.quizeeapp.data.helper

import com.example.quizeeapp.data.model.QuizModel
import com.example.quizeeapp.data.network.ApiClient.Companion.getApi
import com.example.quizeeapp.data.network.ApiInterface
import retrofit2.awaitResponse

class ApiHelper {
    companion object{
        val apiHelper = ApiHelper()
    }
    suspend fun callApi(category: String): QuizModel? {
        val retrofit = getApi()
            .create(ApiInterface::class.java)
            .getData(category = category)
            .awaitResponse()
        if (retrofit.isSuccessful){
            return retrofit.body()
        }else{
            return null
        }
    }
}