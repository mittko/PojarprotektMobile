package com.example.pojarprotektmobileapp.http

import com.example.pojarprotektmobileapp.models.AuthRequest
import com.example.pojarprotektmobileapp.models.LoginRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ISayHello {

    @GET("/hello")
    fun sayHello(@Header("Authorization") accessToken : String) : Call<String>

    @POST("/login")
    fun login(@Body body: AuthRequest) : Call<LoginRes>
}