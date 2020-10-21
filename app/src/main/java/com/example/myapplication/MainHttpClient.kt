package com.example.myapplication

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainHttpClient {

    companion object {
        private lateinit var retrofitClient: Retrofit
        fun getRestClient(url: String): Retrofit {
            val httpClient = OkHttpClient.Builder()
            val okHttpClient = httpClient.build()
            if (retrofitClient == null) {
                retrofitClient = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            }
            return retrofitClient
        }
    }
}