package com.example.firebaseauthpreference

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences (context: Context) {
    private val prefsFilename = "prefs"
    private val prefsKeyEdt = "null"
    private val prefs: SharedPreferences = context.getSharedPreferences(prefsFilename,0)

    var myEditText: String?
        get() = prefs.getString(prefsKeyEdt, "")
        set(value) = prefs.edit().putString(prefsKeyEdt,value).apply()
}