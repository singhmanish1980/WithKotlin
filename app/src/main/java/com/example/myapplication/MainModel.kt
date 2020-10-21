package com.example.myapplication

import com.google.gson.annotations.SerializedName

class MainModel {
    @SerializedName("postId")
    var userId = 0

    @SerializedName("id")
    var id = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("body")
    var body: String? = null

}