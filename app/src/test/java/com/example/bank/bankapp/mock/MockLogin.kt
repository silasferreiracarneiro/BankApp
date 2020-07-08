package com.example.bank.bankapp.mock

import com.example.bank.bankapp.config.read
import com.example.bank.bankapp.data.api.config.ResultApi
import com.example.bank.bankapp.data.api.response.UserAccountResponse

fun getResultApiLoginSucessCall(): ResultApi<UserAccountResponse> =
    ResultApi.createSucess(read("login_sucess.json"))

fun getResultApiLoginErrorPassword(): ResultApi<UserAccountResponse> =
    ResultApi.createSucess(read("login_error.json"))

fun getResultApiLoginFailCall(): ResultApi<UserAccountResponse> =
    ResultApi.createError(Throwable("Erro ao executar a ação"))