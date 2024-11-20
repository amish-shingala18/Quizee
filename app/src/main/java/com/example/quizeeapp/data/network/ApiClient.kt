package com.example.quizeeapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object{
        private const val url="https://opentdb.com/"
        private var retrofit:Retrofit?=null
        fun getApi(): Retrofit {
            if(retrofit==null){
                retrofit=Retrofit
                    .Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}