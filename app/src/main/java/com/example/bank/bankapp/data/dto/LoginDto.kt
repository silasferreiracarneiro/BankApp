package com.example.bank.bankapp.data.dto

import java.io.Serializable

class LoginDto(
    var user: String,
    var password: String
) : Serializable{
}