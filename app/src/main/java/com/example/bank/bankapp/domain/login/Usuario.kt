package com.example.bank.bankapp.domain.login

import java.io.Serializable

class Usuario (
    var userId: Int,
    var name: String,
    var bankAccount: String,
    var agency: String,
    var balance: Double
) : Serializable  {
}