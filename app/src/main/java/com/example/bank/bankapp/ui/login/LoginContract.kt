package com.example.bank.bankapp.ui.login

import com.example.bank.bankapp.data.api.config.ResultApi
import com.example.bank.bankapp.data.api.response.UserAccountResponse
import com.example.bank.bankapp.model.UserAccount

interface LoginContract {

    interface Usecase {
        suspend fun validaUsername(username: String?): Boolean
        suspend fun validaPassword(password: String?): Boolean
        suspend fun login(username: String, password: String): ResultApi<UserAccountResponse>
        suspend fun saveUserPrefs(username: String, password: String): Boolean
        suspend fun getLastUserLogged() : Map<String, String>
    }

    interface Presenter{
        fun login(username: String?, password: String?)
        fun getLastUserLogged()
    }

    interface View {
        fun invalidFields()
        fun passwordInvalido()
        fun usernameInvalido()
        fun errorLogin(message: String?)
        fun sucessCallApi(user: UserAccount?)
        fun setLasUserLogged(username: String?, password: String?)
        fun errorSaveAccountPrefs()
    }
}