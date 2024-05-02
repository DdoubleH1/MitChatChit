package com.example.chatapp.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs private constructor(context: Context, prefFileName: String){
    private val mPrefs: SharedPreferences

    init {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
    }

    var userEmail: String?
        get() = mPrefs.getString(PREF_KEY_USER_EMAIL, "")
        set(value) = mPrefs.edit().putString(PREF_KEY_USER_EMAIL, value).apply()

    companion object{
        private const val PREF_NAME = "chat_app_prefs"
        private const val PREF_KEY_USER_EMAIL = "user_email"

        @Volatile
        private var instance: Prefs? = null

        fun getInstance(context: Context): Prefs{
            return instance ?: synchronized(this){
                instance ?: Prefs(context, PREF_NAME).also { instance = it }
            }
        }
    }
}