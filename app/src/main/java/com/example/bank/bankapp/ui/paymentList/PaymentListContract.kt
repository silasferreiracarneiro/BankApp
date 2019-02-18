package com.example.bank.bankapp.ui.paymentList

import com.example.bank.bankapp.domain.CardList.ListPayment

class PaymentListContract {

    interface View{
        fun getListPayment(idUser: Int)
        fun setMessageUser(message: String)
        fun loadCardListPayment(list: ArrayList<ListPayment>)
    }

    interface Presenter{
        fun getListPayment(idUser: Int)
    }
}