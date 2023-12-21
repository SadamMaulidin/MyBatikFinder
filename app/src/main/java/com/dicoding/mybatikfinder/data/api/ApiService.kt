package com.dicoding.mybatikfinder.data.api

import com.dicoding.mybatikfinder.data.SignInResponse
import com.dicoding.mybatikfinder.data.SignUpResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("/api/signup")
    fun signup(
        @Field("name") name: String?,
        @Field("email") email: String?,
        @Field("password") password: String?
    ): Call<SignUpResponse>

    @FormUrlEncoded
    @POST("/api/login")
    fun signin(
        @Field("email") email: String?,
        @Field("password") password: String?
    ): Call<SignInResponse>
}