package com.example.pojarprotektmobileapp.http

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstance {

    //public const val ACCESS_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLQk9C10L7RgNCz0Lgg0JrQvtCy0LDRh9C60LgiLCJmaXJzdE5hbWUiOiLQk9C10L7RgNCz0Lgg0JrQvtCy0LDRh9C60LgiLCJsYXN0TmFtZSI6ItCz0L7Qs9C4IiwiZXhwIjoxNzMxOTU0MjUyfQ.B_EaH5o2xtySyLuETD_Ug5LO9IBnc6PTMVtJI0VcZlY"

    private const val TEST_URL = "http://192.168.1.3:1526"
    fun getInstance() : Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder().baseUrl(TEST_URL)
            .addConverterFactory(ScalarsConverterFactory.create()) //important
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}