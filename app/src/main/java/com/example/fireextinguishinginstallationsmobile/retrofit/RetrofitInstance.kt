package com.example.fireextinguishinginstallationsmobile.retrofit

import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonCheckedDataModelExtended
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonCheckedDataModelThree
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonCheckedDataModelTwo
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonCheckedModel
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonCountModel
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonDropDownModel
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonDropDownModelTwo
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonFieldModel
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonFieldModelThree
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonFieldModelTwo
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonModel
import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonTextModel
import com.google.gson.GsonBuilder
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    //public const val ACCESS_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLQk9C10L7RgNCz0Lgg0JrQvtCy0LDRh9C60LgiLCJmaXJzdE5hbWUiOiLQk9C10L7RgNCz0Lgg0JrQvtCy0LDRh9C60LgiLCJsYXN0TmFtZSI6ItCz0L7Qs9C4IiwiZXhwIjoxNzMxOTU0MjUyfQ.B_EaH5o2xtySyLuETD_Ug5LO9IBnc6PTMVtJI0VcZlY"

    private const val TEST_URL = "http://192.168.1.171:1526"
    fun getInstance(): Retrofit {

        //   val logging = HttpLoggingInterceptor()
        //   logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            //    .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        val typeFactory = RuntimeTypeAdapterFactory.of(
            JsonModel::class.java,
            "type"// "type" is the JSON key matching your layout
            , true
        ).registerSubtype(JsonCheckedModel::class.java, "checkable")
            .registerSubtype(JsonCheckedDataModelTwo::class.java, "checkable count")
            .registerSubtype(JsonCheckedDataModelThree::class.java, "checkable measure")
            .registerSubtype(JsonCheckedDataModelExtended::class.java, "checkable extended")
            .registerSubtype(JsonCountModel::class.java, "count")
            .registerSubtype(JsonFieldModel::class.java, "field")
            .registerSubtype(JsonFieldModelTwo::class.java, "field data two")
            .registerSubtype(JsonFieldModelThree::class.java, "field data three")
            .registerSubtype(JsonTextModel::class.java, "text")
            .registerSubtype(JsonDropDownModel::class.java, "dropdown")
            .registerSubtype(JsonDropDownModelTwo::class.java, "dropdowntwo")

        val gson = GsonBuilder().setLenient().registerTypeAdapterFactory(typeFactory).create()



        return Retrofit.Builder().baseUrl(TEST_URL)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create()) //important
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}