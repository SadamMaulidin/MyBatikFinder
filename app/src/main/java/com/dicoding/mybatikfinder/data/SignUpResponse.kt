package com.dicoding.mybatikfinder.data

import com.google.gson.annotations.SerializedName

data class SignUpResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)
