package com.example.bank.bankapp.data.dto

import com.example.bank.bankapp.domain.error.StandardError

class PaymenteResponseDto (
    var statementList: Array<PaymentDto>,
    var error: StandardError
)