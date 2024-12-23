package com.example.pojarprotektmobileapp.http

import com.example.pojarprotektmobileapp.models.Client
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface IGetClients {

    @GET("/clients")
    fun getClients(@Header("Authorization") accessToken : String)
    : Call<List<Client>>
}