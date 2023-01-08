package com.application.kotlin.utils

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceHelper @Inject constructor(val context: Context) {

    fun set(key: String, text: String) {
        val pref = context.getSharedPreferences("data", 0).edit()
        pref.putString(key, text).apply()
    }

    fun get(key: String): String {
        val pref = context.getSharedPreferences("data", 0)
        return pref.getString(key, "").toString()
    }

    fun clear() {
        context.getSharedPreferences("data", 0).edit().clear().apply()
    }
}