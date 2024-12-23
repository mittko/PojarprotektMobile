package com.example.pojarprotektmobileapp.http.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class ServiceAPI {

    private Retrofit retrofit;

    public Retrofit getRetrofit(){
        if(retrofit == null) {

            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30,TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .writeTimeout(30,TimeUnit.SECONDS);

            Gson gson = new GsonBuilder().setLenient().create();

            OkHttpClient okHttpClient = builder.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://78.142.42.215:1526")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
