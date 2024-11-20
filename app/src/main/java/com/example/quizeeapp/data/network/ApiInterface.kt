package com.example.quizeeapp.data.network

import com.example.quizeeapp.data.model.QuizModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api.php")
    fun getData(
        @Query("type") type:String="multiple",
        @Query("amount") amount:String="10",
        @Query("difficulty") difficulty:String="easy",
        @Query("category") category:String
    ):Call<QuizModel>
}