package com.example.bank.bankapp.data.dto

import com.example.bank.bankapp.domain.CardList.ListPayment
import java.util.*

class PaymentDto(
    var title: String,
    var desc: String,
    var date: Date,
    var value: Double
) {

    fun toDoamin() : ListPayment {
        return ListPayment(
            title,
            date,
            desc,
            value
        )
    }
}