package com.example.bank.bankapp.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.example.bank.bankapp.utils.Constants
import com.example.bank.bankapp.utils.Constants.PASSWORD
import com.example.bank.bankapp.utils.Constants.USERNAME
import com.example.bank.bankapp.utils.decrypt
import com.example.bank.bankapp.utils.encrypt


class SharedPreferencesManager(context: Context) {

    private val mPrefs: SharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

    fun saveUser(username: String, password: String): Boolean {
        return try {
            mPrefs.edit().putString(USERNAME, username.encrypt()).putString(PASSWORD, password.encrypt()).apply()
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getUser() : Map<String, String> {
        val result = mutableMapOf<String, String>()
        result[USERNAME] = mPrefs.getString(USERNAME, "")?.decrypt() ?: ""
        result[PASSWORD] = mPrefs.getString(PASSWORD, "")?.decrypt() ?: ""
        return result
    }
}