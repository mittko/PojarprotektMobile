package com.example.pojarprotektmobileapp.preferences

import android.content.Context
import android.media.session.MediaSession.Token

class PreferencesManager {

    fun putToken(context : Context, newToken : String) {
        val sharedPreference = context.getSharedPreferences("ACCESS_TOKEN_PREFERENCE",Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("token",newToken)
        editor.apply()
    }

    fun getToken(context: Context) : String {
        val sharedPreferences = context.getSharedPreferences("ACCESS_TOKEN_PREFERENCE",Context.MODE_PRIVATE)
        return "Bearer " + sharedPreferences.getString("token","")
    }

}