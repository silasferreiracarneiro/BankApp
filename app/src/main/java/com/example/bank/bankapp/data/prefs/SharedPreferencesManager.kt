package com.example.bank.bankapp.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.example.bank.bankapp.utils.Constants
import com.example.bank.bankapp.utils.Constants.PASSWORD
import com.example.bank.bankapp.utils.Constants.USERNAME

class SharedPreferencesManager(context: Context) {

    private val mPrefs: SharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

    fun saveUser(username: String, password: String) {
        mPrefs.edit().putString(USERNAME, username).putString(PASSWORD, password).apply()
    }

    fun getUser() : Map<String, String> {
        val result = mutableMapOf<String, String>()
        result[USERNAME] = mPrefs.getString(USERNAME, "") ?: ""
        result[PASSWORD] = mPrefs.getString(PASSWORD, "") ?: ""
        return result
    }
}