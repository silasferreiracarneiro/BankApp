package com.example.bank.bankapp.data.dto

import com.example.bank.bankapp.domain.login.Usuario

class UsuarioDto(
    var userId: Int,
    var name: String,
    var bankAccount: String,
    var agency: String,
    var balance: Double
) {

    fun toDomain() : Usuario{
        return Usuario(
            this.userId,
            this.name,
            this.bankAccount,
            this.agency,
            this.balance
        )
    }
}