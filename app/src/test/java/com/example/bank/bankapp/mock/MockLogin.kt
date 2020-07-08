package com.example.bank.bankapp.mock

import com.example.bank.bankapp.config.read
import com.example.bank.bankapp.data.api.config.ResultApi
import com.example.bank.bankapp.data.api.response.UserAccountResponse

fun getResultSucessLoginRepsoitory(): ResultApi<UserAccountResponse> =
    ResultApi.createSucess(read("login_sucess.json"))