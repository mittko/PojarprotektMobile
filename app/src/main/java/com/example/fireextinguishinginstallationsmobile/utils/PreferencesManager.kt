package com.example.fireextinguishinginstallationsmobile.utils

import android.content.Context
import androidx.core.content.edit

class PreferencesManager {

    fun putToken(context: Context, newToken: String) {
        val sharedPreference =
            context.getSharedPreferences("ACCESS_TOKEN_PREFERENCE", Context.MODE_PRIVATE)
        sharedPreference.edit {
            putString("token", newToken)
            apply()
        }

        val token = getToken(context)
    }

    fun getToken(context: Context): String {
        val sharedPreferences =
            context.getSharedPreferences("ACCESS_TOKEN_PREFERENCE", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", "")
        return "Bearer $token"
    }

    fun setDataLoadingType(context: Context, defaultData: Boolean) {
        val sharedPreferences =
            context.getSharedPreferences("DEFAULT_DATA_TYPE", Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putBoolean("defaultData", defaultData)
            apply()
        }
    }

    fun getDataLoadingType(context: Context): Boolean {
        val sharedPreferences =
            context.getSharedPreferences("DEFAULT_DATA_TYPE", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("defaultData", false)
    }

    fun setObjectId(context: Context, objectId: String) {
        val sharedPreferences =
            context.getSharedPreferences("ObjectId", Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putString("objectId", objectId)
            apply()
        }
    }

    fun getObjectId(context: Context): String {
        val sharedPreferences =
            context.getSharedPreferences("ObjectId", Context.MODE_PRIVATE)
        return sharedPreferences.getString("objectId", "")!!
    }

}