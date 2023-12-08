package com.example.myapplication.activitySharedPreferences

import android.content.Context
import android.content.SharedPreferences

class CustumeSharedPrefs(val context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("Tokens", Context.MODE_PRIVATE)

    fun saveLogin(login:Int) {
        val editor = prefs.edit()
        editor.putInt("SAVE_LOGIN", login)
        editor.apply()
    }

    fun saveEmail(email:String) {
        val editor = prefs.edit()
        editor.putString("SAVE_EMAIL", email)
        editor.apply()
    }

    fun getLogin(): Int = prefs.getInt("SAVE_LOGIN", 0)
}