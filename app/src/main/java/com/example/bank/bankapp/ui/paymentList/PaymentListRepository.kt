package com.example.bank.bankapp.ui.paymentList

import com.example.bank.bankapp.data.api.Api
import com.example.bank.bankapp.data.api.config.ResultApi
import com.example.bank.bankapp.data.api.config.doResquest
import com.example.bank.bankapp.data.api.response.PaymentResponse

class PaymentListRepository(private val api: Api) {

    suspend fun getListPayment(it: Int): ResultApi<PaymentResponse> {
        return doResquest {
            api.listStatement(it).await()
        }
    }
}