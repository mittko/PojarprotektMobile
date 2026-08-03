package com.example.fireextinguishinginstallationsmobile.retrofit

import com.example.fireextinguishinginstallationsmobile.enums.InstallationType
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
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ISunotechAPI {
    @Headers("Content-Type: application/json; charset=utf-8")
    @POST("/insert_sunotech_protokol")
    fun writeProtocol(
        @Body body: MyJsonObject,
        @Header("Authorization") accessToken: String
    ): Call<ResponseBody>

    @POST(value = "/insert_sunotech_default_protocol_data")
    fun writeDefaultProtocolData(@Body body: MyJsonObject, @Header("Authorization") accessToken: String) : Call<ResponseBody>

    @POST()
    fun getProtocolData(
        @Url url: String,
        @Body body: ObjectIdModel,
        @Header("Authorization") accessToken: String
    ): Call<MyJsonObject>

    @GET
    fun getDefaultProtocolData(@Url url: String, @Query("installationType") installationType: String,
                               @Header(value = "Authorization") accessToken : String) : Call<MyJsonObject>

    @POST(value = "/login/to/sunotech")
    fun logIn(@Body authModel: AuthModel): Call<LoginRes>

    @GET(value = "/hello")
    fun sayHello(@Header("Authorization") accessToken: String): Call<String>


}