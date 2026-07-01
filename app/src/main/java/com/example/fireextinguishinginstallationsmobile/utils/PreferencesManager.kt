package com.example.fireextinguishinginstallationsmobile.utils

import android.content.Context
import androidx.core.content.edit

class PreferencesManager {

    fun putAuth(context: Context, user: String?, newToken: String?) {
        val sharedPreference =
            context.getSharedPreferences("AUTH_PREFERENCE", Context.MODE_PRIVATE)
        sharedPreference.edit {
            putString("user", user)
            putString("token", newToken)
            apply()
        }
    }

    fun getUser(context: Context): String {
        val sharedPreferences =
            context.getSharedPreferences("AUTH_PREFERENCE", Context.MODE_PRIVATE)
        val user = sharedPreferences.getString("user", "")
        return "$user"
    }

    fun getToken(context: Context): String {
        val sharedPreferences =
            context.getSharedPreferences("AUTH_PREFERENCE", Context.MODE_PRIVATE)
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
        return sharedPreferences.getString("objectId", "") ?: ""
    }

    fun setBarcodeNumber(context: Context, barcode: String) {
        val sharedPreferences = context.getSharedPreferences("BarcodeNumber", Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putString("barcode", barcode)
            apply()
        }
    }

    fun getBarcode(context: Context): String {
        val sharedPreferences =
            context.getSharedPreferences("BarcodeNumber", Context.MODE_PRIVATE)
        return sharedPreferences.getString("barcode", "") ?: ""
    }

}