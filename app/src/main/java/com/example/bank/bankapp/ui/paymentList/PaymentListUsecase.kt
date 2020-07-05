package com.example.bank.bankapp.ui.paymentList

class PaymentListUsecase(private val repository: PaymentListRepository) : PaymentListContract.Usecase {

    override suspend fun getListPayment(it: Int) =
        repository.getListPayment(it)
}