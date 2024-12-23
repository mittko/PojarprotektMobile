package com.example.pojarprotektmobileapp.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.pojarprotektmobileapp.http.base.ErrorBody;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class JavaDialog {

    public static void showHttpError(Context context, Response response) {
        if(response.code() == 401) {
            Toast.makeText(context,"Неоторизиран достъп",Toast.LENGTH_LONG).show();
            return;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ErrorBody>(){}.getType();
        try(ResponseBody responseBody = response.errorBody()) {
            if(responseBody != null) {
                ErrorBody error = gson.fromJson(responseBody.charStream(), type);
                Toast.makeText(context,error.getError(),Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context,"Грешка!", Toast.LENGTH_LONG).show();
            }
        }
    }
    public static String extractErrorMessage(Response response) {
        if(response.code() == 401) {
            return "Неоторизиран достъп";
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ErrorBody>(){}.getType();
        try(ResponseBody responseBody = response.errorBody()) {
            if(responseBody != null) {
                ErrorBody error = gson.fromJson(responseBody.charStream(), type);
                return error.getError();
            } else {
                return "Неизвестна Грешка!";
            }
        }
    }
    public static void showErrorMessage(Context context, String message) {
        Toast.makeText(context,message, Toast.LENGTH_LONG).show();
    }
}
