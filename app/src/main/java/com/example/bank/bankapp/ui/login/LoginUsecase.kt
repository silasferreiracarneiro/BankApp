package com.example.bank.bankapp.ui.login

import com.example.bank.bankapp.data.prefs.PrefsProvider

class LoginUsecase(private val repository: LoginRepository, private val prefs: PrefsProvider) : LoginContract.Usecase {

    private val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+$).{4,}\$"

    override fun validaUsername(username: String?): Boolean {
        return false
    }

    override fun validaPassword(password: String?): Boolean {
        return false
    }

    override suspend fun login(username: String, password: String)
            = repository.loginNetwork(username, password)

    override fun saveUserPrefs(username: String, password: String) {
        prefs.saveUser(username, password)
    }
}