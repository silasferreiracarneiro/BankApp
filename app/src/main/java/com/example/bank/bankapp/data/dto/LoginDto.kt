package com.example.bank.bankapp.data.dto

import com.example.bank.bankapp.domain.login.Login
import java.io.Serializable

class LoginDto(
    var user: String,
    var password: String
) : Serializable{

    fun toDomain() : Login{
        return Login(user, password)
    }
}