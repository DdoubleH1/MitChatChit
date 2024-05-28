package com.example.chatapp.utils

import android.content.Context
import android.content.SharedPreferences
import timber.log.Timber

class Prefs private constructor(context: Context, prefFileName: String){
    private val mPrefs: SharedPreferences

    init {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
    }

    var userEmail: String?
        get() {
            Timber.tag("HoangDH").e("get user email: ${mPrefs.getString(PREF_KEY_USER_EMAIL, "")}")
            return mPrefs.getString(PREF_KEY_USER_EMAIL, "")
        }
        set(value){
            Timber.tag("HoangDH").e("set user email: $value")
            mPrefs.edit().putString(PREF_KEY_USER_EMAIL, value).apply()
        }

    companion object{
        private const val PREF_NAME = "chat_app_prefs"
        private const val PREF_KEY_USER_EMAIL = "user_email"

        @Volatile
        private var mInstance: Prefs? = null

        @JvmStatic
        fun getInstance(context: Context): Prefs {
            if (mInstance == null) {
                mInstance = Prefs(context, PREF_NAME)
            }
            return mInstance!!
        }
    }
}