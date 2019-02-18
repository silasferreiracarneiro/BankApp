package com.example.bank.bankapp.data.dto

import com.example.bank.bankapp.data.error.StandardErrorDto

class PaymenteResponseDto (
    var statementList: Array<PaymentDto>,
    var error: StandardErrorDto
)