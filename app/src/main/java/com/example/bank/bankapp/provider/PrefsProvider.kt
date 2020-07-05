package com.example.bank.bankapp.provider

import android.content.Context
import com.example.bank.bankapp.data.prefs.SharedPreferencesManager

fun providerPrefs(context: Context): SharedPreferencesManager {
    return SharedPreferencesManager(context)
}