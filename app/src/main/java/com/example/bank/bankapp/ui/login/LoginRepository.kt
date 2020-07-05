package com.example.bank.bankapp.ui.login

import com.example.bank.bankapp.data.api.Api
import com.example.bank.bankapp.data.api.config.ResultApi
import com.example.bank.bankapp.data.api.config.doResquest
import com.example.bank.bankapp.data.api.response.UserAccountResponse

class LoginRepository(private val api: Api) {

    suspend fun loginNetwork(login: String, password: String): ResultApi<UserAccountResponse> {
        return doResquest {
            api.login(login, password).await()
        }
    }
}