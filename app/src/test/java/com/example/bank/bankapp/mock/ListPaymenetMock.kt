package com.example.bank.bankapp.mock

import com.example.bank.bankapp.config.read
import com.example.bank.bankapp.data.api.config.ResultApi
import com.example.bank.bankapp.data.api.response.PaymentResponse
import com.example.bank.bankapp.data.api.response.UserAccountResponse
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

fun getResultApiListPaymentSucessCall(): ResultApi<PaymentResponse> =
    ResultApi.createSucess(read("lista_pagamento.json"))

fun getResultApiListPaymentErrorCall(): ResultApi<PaymentResponse> =
    ResultApi.createSucess(read("lista_pagamento_error.json"))

fun getResultApiListPaymentEmpty(): ResultApi<PaymentResponse> =
    ResultApi.createSucess(read("lista_pagamento_empty.json"))

fun getResultApiPaymentSucess(): Deferred<PaymentResponse> {
    val lista = read<PaymentResponse>("lista_pagamento.json")
    return CompletableDeferred(lista)
}

fun getResultApiPaymentEmpty(): Deferred<PaymentResponse> {
    val lista = read<PaymentResponse>("lista_pagamento_empty.json")
    return CompletableDeferred(lista)
}

fun getResultApiPaymentError(): Deferred<PaymentResponse> {
    val lista = read<PaymentResponse>("lista_pagamento_error.json")
    return CompletableDeferred(lista)
}