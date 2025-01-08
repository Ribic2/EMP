package com.example.emp_vid_matej.service

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.emp_vid_matej.R

class SessionManager(context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)


    companion object {
        const val USER_TOKEN = "user_token"
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun removeToken() {
        prefs.edit().remove(USER_TOKEN).apply();
    }
}