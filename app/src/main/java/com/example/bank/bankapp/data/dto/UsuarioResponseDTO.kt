package com.example.bank.bankapp.data.dto

import com.example.bank.bankapp.domain.error.StandardError

class UsuarioResponseDTO (
    var userAccount: UsuarioDto,
    var error: StandardError
){
}