package com.example.bank.bankapp.domain.CardList

import java.util.*

class ListPayment(
    var typePayment: String = "",
    var date: Date? = Date(),
    var typeAccount: String = "",
    var valuePayment: Double = 0.0
)