package com.example.myapplication

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface MainApiResponse {
    @get:GET("/comments")
    val comments: Call<ResponseBody>
}