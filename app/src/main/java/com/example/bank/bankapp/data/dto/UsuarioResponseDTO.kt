package com.example.bank.bankapp.data.dto

import com.example.bank.bankapp.data.error.StandardErrorDto

class UsuarioResponseDTO (
    var userAccount: UsuarioDto,
    var error: StandardErrorDto
){
}