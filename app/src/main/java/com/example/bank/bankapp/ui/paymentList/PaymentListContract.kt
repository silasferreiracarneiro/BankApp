package com.example.bank.bankapp.ui.paymentList

import com.example.bank.bankapp.data.api.config.ResultApi
import com.example.bank.bankapp.data.api.response.PaymentResponse
import com.example.bank.bankapp.model.Payment

class PaymentListContract {

    interface Usecase{
        suspend fun getListPayment(it: Int): ResultApi<PaymentResponse>
    }

    interface View{
        fun showErrorUser(message: String?)
        fun sucessCallApi(parseStatementResponseToPayment: List<Payment>?)
    }

    interface Presenter{
        fun getListPayment(it: Int)
    }
}