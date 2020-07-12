package com.example.bank.bankapp.ui.login

import com.example.bank.bankapp.data.prefs.SharedPreferencesManager
import com.example.bank.bankapp.utils.isValidCpf
import com.example.bank.bankapp.utils.isValidEmail
import java.util.regex.Pattern

class LoginUsecase(private val repository: LoginRepository, private val prefs: SharedPreferencesManager) : LoginContract.Usecase {

    private val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+$).{4,}\$"

    override suspend fun validaUsername(username: String?): Boolean {
        return if (username == null || username.isBlank() || username.isEmpty())
            true
        else !(username.isValidCpf() || username.isValidEmail())
    }

    override suspend fun validaPassword(password: String?): Boolean {
        if(password == null)
            return true
        val pattern = Pattern.compile(passwordPattern)
        val matcher = pattern.matcher(password)
        return !matcher.matches()
    }

    override suspend fun login(username: String, password: String)
            = repository.login(username, password)

    override suspend fun saveUserPrefs(username: String, password: String): Boolean =
        prefs.saveUser(username, password)

    override suspend fun getLastUserLogged(): Map<String, String> = prefs.getUser()
}