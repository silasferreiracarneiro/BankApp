package com.example.bank.bankapp.provider

import com.example.bank.bankapp.ui.paymentList.PaymentListContract
import com.example.bank.bankapp.ui.paymentList.PaymentListRepository
import com.example.bank.bankapp.ui.paymentList.PaymentListUsecase

private fun providerPaymentListRepository() : PaymentListRepository {
    return PaymentListRepository(providerApi())
}

fun providerPaymentListUsecase() : PaymentListContract.Usecase {
    return PaymentListUsecase(providerPaymentListRepository())
}