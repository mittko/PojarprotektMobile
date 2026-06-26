package com.example.fireextinguishinginstallationsmobile.retrofit

import com.example.fireextinguishinginstallationsmobile.json.MyJsonObject
import com.example.fireextinguishinginstallationsmobile.json.ObjectIdModel
import com.example.fireextinguishinginstallationsmobile.models.auth.AuthModel
import com.example.fireextinguishinginstallationsmobile.models.auth.LoginRes
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ISunotechAPI {
    @Headers("Content-Type: application/json; charset=utf-8")
    @POST("/insert_sunotech_protokol")
    fun writeProtokol(@Body body: MyJsonObject): Call<ResponseBody>


    @POST(value = "/get_sunotech_protokol_details")
    fun getProtokolData(
        @Body body: ObjectIdModel, @Query("defaultDocument") defaultDocument: String,
        @Header("Authorization") accessToken: String
    ): Call<MyJsonObject>

    @POST(value = "/login/to/sunotech")
    fun logIn(@Body authModel: AuthModel): Call<LoginRes>

    @GET(value = "/hello")
    fun sayHello(@Header("Authorization") accessToken: String): Call<String>


}