package com.example.bank.bankapp.ui.paymentList

import com.example.bank.bankapp.data.api.config.ResultApi
import com.example.bank.bankapp.data.api.response.PaymentResponse
import com.example.bank.bankapp.provider.providerPaymentListUsecase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PaymentListPresenter(private val view: PaymentListContract.View,
                           private val usercase: PaymentListContract.Usecase = providerPaymentListUsecase()):
    PaymentListContract.Presenter {

    override fun getListPayment(it: Int) {
        GlobalScope.launch {
            val listPayment = usercase.getListPayment(it)
            afterCall(
                listPayment
            )
        }
    }

    private fun afterCall(listPayment: ResultApi<PaymentResponse>) {
        when (listPayment.isSucess()) {
            true -> validatedResponseApi(listPayment.value)
            false -> view.showErrorUser(listPayment.error?.message)
        }
    }

    private fun validatedResponseApi(value: PaymentResponse?) {
        when (value?.errorResponse?.message == null) {
            true -> view.sucessCallApi(value?.parseStatementResponseToPayment())
            false -> view.showErrorUser(value?.errorResponse?.message)
        }
    }
}