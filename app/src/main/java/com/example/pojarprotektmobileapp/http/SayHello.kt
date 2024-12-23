package com.example.pojarprotektmobileapp.http

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.pojarprotektmobileapp.models.HttpResponse
import com.example.pojarprotektmobileapp.utils.JavaDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SayHello {

    @Composable
    public fun SayHello(token : String,  res : (HttpResponse) -> Unit)  {

        LaunchedEffect(Unit) {
            val retrofitInstance = RetrofitInstance.getInstance().create(ISayHello::class.java)
            retrofitInstance.sayHello(token).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    res.invoke(HttpResponse(response.code(),response.message()))
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    res.invoke(HttpResponse(504,t.message!!))
                }

            })
        }


    }
}