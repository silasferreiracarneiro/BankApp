package com.example.bank.bankapp.ui.login

import com.example.bank.bankapp.data.api.config.ResultApi
import com.example.bank.bankapp.data.api.response.UserAccountResponse
import com.example.bank.bankapp.model.UserAccount

interface LoginContract {

    interface Usecase {
        fun validaUsername(username: String?): Boolean
        fun validaPassword(password: String?): Boolean
        suspend fun login(username: String, password: String): ResultApi<UserAccountResponse>
        fun saveUserPrefs(username: String, password: String)
        fun getLastUserLogged() : Map<String, String>
    }

    interface Presenter{
        fun login(username: String?, password: String?)
        fun getLastUserLogged()
    }

    interface View{
        fun invalidFields()
        fun passwordInvalido()
        fun usernameInvalido()
        fun errorLogin(message: String?)
        fun sucessCallApi(user: UserAccount?)
        fun setLasUserLogged(username: String?, password: String?)
    }
}