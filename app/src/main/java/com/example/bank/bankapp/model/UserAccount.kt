package com.example.bank.bankapp.model

import java.io.Serializable

class UserAccount (
    val userId : Int,
    val name : String,
    val bankAccount : Int,
    val agency : Int,
    val balance : Double
) : Serializable