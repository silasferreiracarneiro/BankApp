package com.example.bank.bankapp.mock

import com.example.bank.bankapp.config.Contants.PASSWORD
import com.example.bank.bankapp.config.Contants.USERNAME
import com.example.bank.bankapp.config.read
import com.example.bank.bankapp.data.api.config.ResultApi
import com.example.bank.bankapp.data.api.request.UserRequest
import com.example.bank.bankapp.data.api.response.UserAccountResponse
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

fun getResultApiLoginSucessCall(): ResultApi<UserAccountResponse> =
    ResultApi.createSucess(read("login_sucess.json"))

fun getResultApiLoginErrorPassword(): ResultApi<UserAccountResponse> =
    ResultApi.createSucess(read("login_error.json"))

fun getResultApiLoginFailCall(): ResultApi<UserAccountResponse> =
    ResultApi.createError(Throwable("Erro ao executar a ação"))

fun getUserRequest() = UserRequest(USERNAME, PASSWORD)

fun getResultApiLoginSucess(): Deferred<UserAccountResponse> {
    val userAccount = read<UserAccountResponse>("login_sucess.json")
    return CompletableDeferred(userAccount)
}

fun getResultApiLoginError() = CompletableDeferred<UserAccountResponse>()